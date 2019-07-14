public static AlertDialog.Builder createContactsPermissionDialog(final Activity parentActivity,final MessagesStorage.IntCallback callback){
  AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
  builder.setTopImage(R.drawable.permissions_contacts,Theme.getColor(Theme.key_dialogTopBackground));
  builder.setMessage(AndroidUtilities.replaceTags(LocaleController.getString("ContactsPermissionAlert",R.string.ContactsPermissionAlert)));
  builder.setPositiveButton(LocaleController.getString("ContactsPermissionAlertContinue",R.string.ContactsPermissionAlertContinue),(dialog,which) -> callback.run(1));
  builder.setNegativeButton(LocaleController.getString("ContactsPermissionAlertNotNow",R.string.ContactsPermissionAlertNotNow),(dialog,which) -> callback.run(0));
  return builder;
}
