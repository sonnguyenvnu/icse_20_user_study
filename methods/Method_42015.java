@OnClick(R.id.ll_accentColor) public void onChangeAccentColorClicked(View view){
  final int originalColor=getAccentColor();
  new ColorsSetting(SettingsActivity.this).chooseColor(R.string.accent_color,new ColorsSetting.ColorChooser(){
    @Override public void onColorSelected(    int color){
      Hawk.put(getString(R.string.preference_accent_color),color);
      updateTheme();
      updateUiElements();
    }
    @Override public void onDialogDismiss(){
      Hawk.put(getString(R.string.preference_accent_color),originalColor);
      updateTheme();
      updateUiElements();
    }
    @Override public void onColorChanged(    int color){
      Hawk.put(getString(R.string.preference_accent_color),color);
      updateTheme();
      updateUiElements();
    }
  }
,getAccentColor());
}
