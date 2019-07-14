@OnClick(R.id.ll_primaryColor) public void onChangePrimaryColorClicked(View view){
  final int originalColor=getPrimaryColor();
  new ColorsSetting(SettingsActivity.this).chooseColor(R.string.primary_color,new ColorsSetting.ColorChooser(){
    @Override public void onColorSelected(    int color){
      Hawk.put(getString(R.string.preference_primary_color),color);
      updateTheme();
      updateUiElements();
    }
    @Override public void onDialogDismiss(){
      Hawk.put(getString(R.string.preference_primary_color),originalColor);
      updateTheme();
      updateUiElements();
    }
    @Override public void onColorChanged(    int color){
      Hawk.put(getString(R.string.preference_primary_color),color);
      updateTheme();
      updateUiElements();
    }
  }
,getPrimaryColor());
}
