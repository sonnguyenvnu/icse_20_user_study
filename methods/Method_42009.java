@CallSuper @Override public void updateUiElements(){
  super.updateUiElements();
  findViewById(org.horaapps.leafpic.R.id.setting_background).setBackgroundColor(getBackgroundColor());
  setStatusBarColor();
  setNavBarColor();
  setRecentApp(getString(org.horaapps.leafpic.R.string.settings));
}
