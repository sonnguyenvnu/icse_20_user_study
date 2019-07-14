@TargetApi(Build.VERSION_CODES.M) private void askForPermissons(boolean alert){
  Activity activity=getParentActivity();
  if (activity == null || !UserConfig.getInstance(currentAccount).syncContacts || activity.checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
    return;
  }
  if (alert && askAboutContacts) {
    AlertDialog.Builder builder=AlertsCreator.createContactsPermissionDialog(activity,param -> {
      askAboutContacts=param != 0;
      if (param == 0) {
        return;
      }
      askForPermissons(false);
    }
);
    showDialog(builder.create());
    return;
  }
  ArrayList<String> permissons=new ArrayList<>();
  permissons.add(Manifest.permission.READ_CONTACTS);
  permissons.add(Manifest.permission.WRITE_CONTACTS);
  permissons.add(Manifest.permission.GET_ACCOUNTS);
  String[] items=permissons.toArray(new String[0]);
  activity.requestPermissions(items,1);
}
