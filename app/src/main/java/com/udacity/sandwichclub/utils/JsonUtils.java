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
        String sandwichMainName = "";
        JSONArray sandwichAKAArray = null;
        String sandwichOrigin;
        String sandwichDescription;
        String sandwichImage;
        JSONArray sandwichIngredientsArray;
        try {
            sandwichJson = new JSONObject(json);
            sandwichName = sandwichJson.optJSONObject("name");
            if (sandwichName != null) {
                sandwichMainName = sandwichName.optString("mainName");
                sandwichAKAArray = sandwichName.optJSONArray("alsoKnownAs");
            }
            sandwichOrigin = sandwichJson.optString("placeOfOrigin");
            sandwichDescription = sandwichJson.optString("description");
            sandwichImage = sandwichJson.optString("image");
            sandwichIngredientsArray = sandwichJson.optJSONArray("ingredients");
        } catch (JSONException e) {
            Log.e(JsonUtils.class.getSimpleName(), "JSON parsing error");
            return null;
        }

        ArrayList<String> sandwichAKA = new ArrayList<>();
        if (sandwichAKAArray != null) {
            for (int i = 0; i < sandwichAKAArray.length(); i++) {
                sandwichAKA.add(sandwichAKAArray.optString(i));
            }
        }

        ArrayList<String> sandwichIngredients = new ArrayList<>();
        if (sandwichIngredientsArray != null) {
            for (int i = 0; i < sandwichIngredientsArray.length(); i++) {
                sandwichIngredients.add(sandwichIngredientsArray.optString(i));
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
