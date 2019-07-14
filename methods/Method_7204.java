public static boolean isSecretMapPreviewSet(){
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  return preferences.contains("mapPreviewType");
}
