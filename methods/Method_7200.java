public static void toggleAutoplayGifs(){
  autoplayGifs=!autoplayGifs;
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("autoplay_gif",autoplayGifs);
  editor.commit();
}
