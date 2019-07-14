public static void toggleCustomTabs(){
  customTabs=!customTabs;
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("custom_tabs",customTabs);
  editor.commit();
}
