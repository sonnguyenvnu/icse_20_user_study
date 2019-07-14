public static void toggleStreamMkv(){
  streamMkv=!streamMkv;
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("streamMkv",streamMkv);
  editor.commit();
}
