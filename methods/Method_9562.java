private void processDone(){
  if (getParentActivity() == null) {
    return;
  }
  if (currentType != 0 && rulesType == PRIVACY_RULES_TYPE_LASTSEEN) {
    final SharedPreferences preferences=MessagesController.getGlobalMainSettings();
    boolean showed=preferences.getBoolean("privacyAlertShowed",false);
    if (!showed) {
      AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
      if (rulesType == PRIVACY_RULES_TYPE_INVITE) {
        builder.setMessage(LocaleController.getString("WhoCanAddMeInfo",R.string.WhoCanAddMeInfo));
      }
 else {
        builder.setMessage(LocaleController.getString("CustomHelp",R.string.CustomHelp));
      }
      builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
      builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> {
        applyCurrentPrivacySettings();
        preferences.edit().putBoolean("privacyAlertShowed",true).commit();
      }
);
      builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
      showDialog(builder.create());
      return;
    }
  }
  applyCurrentPrivacySettings();
}
