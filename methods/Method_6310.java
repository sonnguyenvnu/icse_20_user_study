private void saveContactsLoadTime(){
  try {
    SharedPreferences preferences=MessagesController.getMainSettings(currentAccount);
    preferences.edit().putLong("lastReloadStatusTime",System.currentTimeMillis()).commit();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
