package com.reactnativenavigation.parse;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class NavigationOptions implements DEFAULT_VALUES {

	public enum BooleanOptions {
		True,
		False,
		NoValue;

		static BooleanOptions parse(String value) {
			if (value != null && !value.equals("")) {
				return Boolean.valueOf(value) ? True : False;
			}
			return NoValue;
		}
	}

	@NonNull
	public static NavigationOptions parse(JSONObject json) {
		NavigationOptions result = new NavigationOptions();
		if (json == null) return result;

		result.topBarOptions = TopBarOptions.parse(json.optJSONObject("topBar"));
		result.bottomTabsOptions = BottomTabsOptions.parse(json.optJSONObject("tabBar"));

		JSONArray rightOptionsJson = json.optJSONArray("rightButtons");
		if (rightOptionsJson != null) {
			int length = rightOptionsJson.length();
			for (int i = 0; i < length; i++) {
				TopBarButtonOptions buttonOptions = TopBarButtonOptions.parse(rightOptionsJson.optJSONObject(i));
				result.rightButtons.add(buttonOptions);
			}
		}

		return result;
	}

	public TopBarOptions topBarOptions = new TopBarOptions();
	public BottomTabsOptions bottomTabsOptions = new BottomTabsOptions();
	public ArrayList<TopBarButtonOptions> rightButtons = new ArrayList<>();

	public void mergeWith(final NavigationOptions other) {
		topBarOptions.mergeWith(other.topBarOptions);
		bottomTabsOptions.mergeWith(other.bottomTabsOptions);
		if (other.rightButtons != null && !other.rightButtons.isEmpty()) {
			this.rightButtons.clear();
			for (TopBarButtonOptions buttonOptions : other.rightButtons) {
				this.rightButtons.add(buttonOptions.clone());
			}
		}
	}
}
