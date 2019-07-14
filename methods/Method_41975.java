@CallSuper @Override public void updateUiElements(){
  super.updateUiElements();
  toolbar.setPopupTheme(getPopupToolbarStyle());
  toolbar.setBackgroundColor(getPrimaryColor());
  setStatusBarColor();
  setNavBarColor();
  fab.setBackgroundTintList(ColorStateList.valueOf(getAccentColor()));
  fab.setVisibility(Hawk.get(getString(R.string.preference_show_fab),false) ? View.VISIBLE : View.GONE);
  mainLayout.setBackgroundColor(getBackgroundColor());
  setAllScrollbarsColor();
  navigationDrawerView.setTheme(getPrimaryColor(),getBackgroundColor(),getTextColor(),getIconColor());
  setRecentApp(getString(R.string.app_name));
}
