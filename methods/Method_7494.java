public void switchBackend(){
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  preferences.edit().remove("language_showed2").commit();
  native_switchBackend(currentAccount);
}
