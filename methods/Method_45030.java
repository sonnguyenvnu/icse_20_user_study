public void onThemesChanged(){
  this.getModel().changeTheme(luytenPrefs.getThemeXml());
  luytenPrefs.setFont_size(this.getModel().getTheme().baseFont.getSize());
}
