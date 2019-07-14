public static void toggleInappCamera(){
  inappCamera=!inappCamera;
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("inappCamera",inappCamera);
  editor.commit();
}
