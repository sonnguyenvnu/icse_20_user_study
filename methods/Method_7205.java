public static void toogleRaiseToSpeak(){
  raiseToSpeak=!raiseToSpeak;
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("raise_to_speak",raiseToSpeak);
  editor.commit();
}
