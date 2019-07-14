public void setPlayProfileAnimation(boolean value){
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  if (!AndroidUtilities.isTablet() && preferences.getBoolean("view_animations",true)) {
    playProfileAnimation=value;
  }
}
