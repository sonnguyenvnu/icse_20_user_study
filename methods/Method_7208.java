public static void toggleStreamMedia(){
  streamMedia=!streamMedia;
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("streamMedia",streamMedia);
  editor.commit();
}
