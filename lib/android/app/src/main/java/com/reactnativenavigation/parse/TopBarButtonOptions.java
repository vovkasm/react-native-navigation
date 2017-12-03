package com.reactnativenavigation.parse;

import org.json.JSONObject;

public class TopBarButtonOptions implements DEFAULT_VALUES {
	public static TopBarButtonOptions parse(JSONObject json) {
		TopBarButtonOptions options = new TopBarButtonOptions();
		if (json == null) return options;

		options.id = json.optString("id", NO_VALUE);
		options.title = json.optString("title", NO_VALUE);

		return options;
	}

	public String id = NO_VALUE;
	public String title = NO_VALUE;

	public boolean hasId() {
		return !NO_VALUE.equals(this.id);
	}
	public boolean hasTitle() {
		return !NO_VALUE.equals(this.title);
	}

	void mergeWith(final TopBarButtonOptions other) {
		if (other.hasId()) this.id = other.id;
		if (other.hasTitle()) this.title = other.title;
	}

	public TopBarButtonOptions clone() {
		TopBarButtonOptions options = new TopBarButtonOptions();
		options.id = this.id;
		options.title = this.title;
		return options;
	}
}
