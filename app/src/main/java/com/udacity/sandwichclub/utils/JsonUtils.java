package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        JSONObject sandwichJson;
        JSONObject sandwichName;
        String sandwichMainName;
        JSONArray sandwichAKAArray;
        String sandwichOrigin;
        String sandwichDescription;
        String sandwichImage;
        JSONArray sandwichIngredientsArray;
        try {
            sandwichJson = new JSONObject(json);
            sandwichName = sandwichJson.getJSONObject("name");
            sandwichMainName = sandwichName.getString("mainName");
            sandwichAKAArray = sandwichName.getJSONArray("alsoKnownAs");
            sandwichOrigin = sandwichJson.getString("placeOfOrigin");
            sandwichDescription = sandwichJson.getString("description");
            sandwichImage = sandwichJson.getString("image");
            sandwichIngredientsArray = sandwichJson.getJSONArray("ingredients");
        } catch (JSONException e) {
            Log.e(JsonUtils.class.getSimpleName(), "JSON parsing error");
            return null;
        }

        ArrayList<String> sandwichAKA = new ArrayList<>();
        for (int i = 0; i < sandwichAKAArray.length(); i++) {
            try {
                sandwichAKA.add(sandwichAKAArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ArrayList<String> sandwichIngredients = new ArrayList<>();
        for (int i = 0; i < sandwichIngredientsArray.length(); i++) {
            try {
                sandwichIngredients.add(sandwichIngredientsArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return new Sandwich(
                sandwichMainName,
                sandwichAKA,
                sandwichOrigin,
                sandwichDescription,
                sandwichImage,
                sandwichIngredients
        );
    }
}
