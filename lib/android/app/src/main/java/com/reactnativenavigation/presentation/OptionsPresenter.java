package com.reactnativenavigation.presentation;

import android.view.View;

import com.reactnativenavigation.anim.StackAnimator;
import com.reactnativenavigation.parse.NavigationOptions;
import com.reactnativenavigation.utils.TypefaceLoader;
import com.reactnativenavigation.viewcontrollers.ContainerViewController;
import com.reactnativenavigation.views.TopbarContainerView;

public class OptionsPresenter {

	private ContainerViewController controller;
	private final StackAnimator animator;

	public OptionsPresenter(ContainerViewController controller) {
		this.controller = controller;
		animator = new StackAnimator(controller.getActivity());
	}

	public void applyOptions(NavigationOptions options) {
		if (controller != null && controller.getTopBar() != null) {
			if (options.topBarOptions.hasTitle()) {
				controller.getTopBar().setTitle(options.topBarOptions.title);
			}
			if (options.topBarOptions.hasBackgroundColor()) {
				controller.getTopBar().setBackgroundColor(options.topBarOptions.backgroundColor);
			}
			if (options.topBarOptions.hasTextColor()) {
				controller.getTopBar().setTitleTextColor(options.topBarOptions.textColor);
			}
			if (options.topBarOptions.hasTextFontSize()) {
				controller.getTopBar().setTitleFontSize(options.topBarOptions.textFontSize);
			}
			if (options.topBarOptions.hasTextFontFamily()) {
				TypefaceLoader typefaceLoader = new TypefaceLoader();
				controller.getTopBar().setTitleTypeface(typefaceLoader.getTypeFace(controller.getActivity(), options.topBarOptions.textFontFamily));
			}
			applyTopbarHiddenOptions(options);
		}
	}

	private void applyTopbarHiddenOptions(NavigationOptions options) {
		if (options.topBarOptions.hidden == NavigationOptions.BooleanOptions.True) {
			hideTopbar(options.topBarOptions.animateHide);
		}
		if (options.topBarOptions.hidden == NavigationOptions.BooleanOptions.False) {
			showTopbar(options.topBarOptions.animateHide);
		}
	}

	private void showTopbar(NavigationOptions.BooleanOptions animated) {
		if (controller.getTopBar().getVisibility() == View.VISIBLE) {
			return;
		}
		if (animated == NavigationOptions.BooleanOptions.True) {
			TopbarContainerView topbarContainerView = (TopbarContainerView) controller.getContainerView();
			animator.animateShowTopBar(controller.getTopBar(), topbarContainerView.getContainerView().asView());
		} else {
			controller.getTopBar().setVisibility(View.VISIBLE);
		}
	}

	private void hideTopbar(NavigationOptions.BooleanOptions animated) {
		if (controller.getTopBar().getVisibility() == View.GONE) {
			return;
		}
		if (animated == NavigationOptions.BooleanOptions.True) {
			TopbarContainerView topbarContainerView = (TopbarContainerView) controller.getContainerView();
			animator.animateHideTopBar(controller.getTopBar(), topbarContainerView.getContainerView().asView());
		} else {
			controller.getTopBar().setVisibility(View.GONE);
		}
	}
}
