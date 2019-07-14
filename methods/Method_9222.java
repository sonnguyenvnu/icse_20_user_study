@TargetApi(Build.VERSION_CODES.M) private void askForPermissons(boolean alert){
  Activity activity=getParentActivity();
  if (activity == null) {
    return;
  }
  ArrayList<String> permissons=new ArrayList<>();
  if (getUserConfig().syncContacts && askAboutContacts && activity.checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
    if (alert) {
      AlertDialog.Builder builder=AlertsCreator.createContactsPermissionDialog(activity,param -> {
        askAboutContacts=param != 0;
        MessagesController.getGlobalNotificationsSettings().edit().putBoolean("askAboutContacts",askAboutContacts).commit();
        askForPermissons(false);
      }
);
      showDialog(permissionDialog=builder.create());
      return;
    }
    permissons.add(Manifest.permission.READ_CONTACTS);
    permissons.add(Manifest.permission.WRITE_CONTACTS);
    permissons.add(Manifest.permission.GET_ACCOUNTS);
  }
  if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
    permissons.add(Manifest.permission.READ_EXTERNAL_STORAGE);
    permissons.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
  }
  if (permissons.isEmpty()) {
    return;
  }
  String[] items=permissons.toArray(new String[0]);
  try {
    activity.requestPermissions(items,1);
  }
 catch (  Exception ignore) {
  }
}
