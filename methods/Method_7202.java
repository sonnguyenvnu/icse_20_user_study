public static void toggleArchiveHidden(){
  archiveHidden=!archiveHidden;
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("archiveHidden",archiveHidden);
  editor.commit();
}
