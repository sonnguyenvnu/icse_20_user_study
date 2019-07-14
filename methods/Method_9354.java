private void showExceptionsAlert(int position){
  ArrayList<NotificationException> exceptions;
  String alertText=null;
  if (position == privateRow) {
    exceptions=exceptionUsers;
    if (exceptions != null && !exceptions.isEmpty()) {
      alertText=LocaleController.formatPluralString("ChatsException",exceptions.size());
    }
  }
 else   if (position == groupRow) {
    exceptions=exceptionChats;
    if (exceptions != null && !exceptions.isEmpty()) {
      alertText=LocaleController.formatPluralString("Groups",exceptions.size());
    }
  }
 else {
    exceptions=exceptionChannels;
    if (exceptions != null && !exceptions.isEmpty()) {
      alertText=LocaleController.formatPluralString("Channels",exceptions.size());
    }
  }
  if (alertText == null) {
    return;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  if (exceptions.size() == 1) {
    builder.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("NotificationsExceptionsSingleAlert",R.string.NotificationsExceptionsSingleAlert,alertText)));
  }
 else {
    builder.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("NotificationsExceptionsAlert",R.string.NotificationsExceptionsAlert,alertText)));
  }
  builder.setTitle(LocaleController.getString("NotificationsExceptions",R.string.NotificationsExceptions));
  builder.setNeutralButton(LocaleController.getString("ViewExceptions",R.string.ViewExceptions),(dialogInterface,i) -> presentFragment(new NotificationsCustomSettingsActivity(-1,exceptions)));
  builder.setNegativeButton(LocaleController.getString("OK",R.string.OK),null);
  showDialog(builder.create());
}
