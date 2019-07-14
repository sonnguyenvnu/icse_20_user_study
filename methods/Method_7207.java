public static void toggleDirectShare(){
  directShare=!directShare;
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("direct_share",directShare);
  editor.commit();
}
