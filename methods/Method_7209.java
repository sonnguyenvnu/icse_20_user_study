public static void toggleSortContactsByName(){
  sortContactsByName=!sortContactsByName;
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("sortContactsByName",sortContactsByName);
  editor.commit();
}
