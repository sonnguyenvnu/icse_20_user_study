public static void toggleAutoplayVideo(){
  autoplayVideo=!autoplayVideo;
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("autoplay_video",autoplayVideo);
  editor.commit();
}
