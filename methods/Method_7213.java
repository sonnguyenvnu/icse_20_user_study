public static void toggleRoundCamera16to9(){
  roundCamera16to9=!roundCamera16to9;
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("roundCamera16to9",roundCamera16to9);
  editor.commit();
}
