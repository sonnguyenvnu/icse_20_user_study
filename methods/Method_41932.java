@CallSuper @Override public void updateUiElements(){
  super.updateUiElements();
  toolbar.setBackgroundColor(getPrimaryColor());
  toolbar.setNavigationIcon(getToolbarIcon(GoogleMaterial.Icon.gmd_arrow_back));
  toolbar.setNavigationOnClickListener(v -> onBackPressed());
  setScrollViewColor(aboutScrollView);
  setStatusBarColor();
  setNavBarColor();
  specialThanksPatryk.setLinkTextColor(getAccentColor());
}
