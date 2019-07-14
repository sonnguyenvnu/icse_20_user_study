public static void toggleStreamAllVideo(){
  streamAllVideo=!streamAllVideo;
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("streamAllVideo",streamAllVideo);
  editor.commit();
}
