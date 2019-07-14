public static void toggleRepeatMode(){
  repeatMode++;
  if (repeatMode > 2) {
    repeatMode=0;
  }
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putInt("repeatMode",repeatMode);
  editor.commit();
}
