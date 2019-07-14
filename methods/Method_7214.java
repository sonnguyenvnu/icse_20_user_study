public static void toggleGroupPhotosEnabled(){
  groupPhotosEnabled=!groupPhotosEnabled;
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("groupPhotosEnabled",groupPhotosEnabled);
  editor.commit();
}
