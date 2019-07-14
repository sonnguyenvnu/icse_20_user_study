@Override protected Void doInBackground(String... strings){
  String mime=strings[0];
  Intent shareIntent=new Intent();
  boolean bluetooth_present=false;
  shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
  shareIntent.setType(mime);
  PackageManager packageManager=contextc.getPackageManager();
  List<ResolveInfo> resInfos=packageManager.queryIntentActivities(shareIntent,0);
  if (!resInfos.isEmpty()) {
    for (    ResolveInfo resInfo : resInfos) {
      String packageName=resInfo.activityInfo.packageName;
      arrayList2.add(resInfo.loadIcon(packageManager));
      arrayList1.add(resInfo.loadLabel(packageManager).toString());
      if (packageName.contains("android.bluetooth"))       bluetooth_present=true;
      Intent intent=new Intent();
      System.out.println(resInfo.activityInfo.packageName + "\t" + resInfo.activityInfo.name);
      intent.setComponent(new ComponentName(packageName,resInfo.activityInfo.name));
      intent.setAction(Intent.ACTION_SEND_MULTIPLE);
      intent.setType(mime);
      intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,arrayList);
      intent.setPackage(packageName);
      targetShareIntents.add(intent);
    }
  }
  if (!bluetooth_present && appInstalledOrNot("com.android.bluetooth",packageManager)) {
    Intent intent=new Intent();
    intent.setComponent(new ComponentName("com.android.bluetooth","com.android.bluetooth.opp.BluetoothOppLauncherActivity"));
    intent.setAction(Intent.ACTION_SEND_MULTIPLE);
    intent.setType(mime);
    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,arrayList);
    intent.setPackage("com.android.bluetooth");
    targetShareIntents.add(intent);
    arrayList1.add(contextc.getString(R.string.bluetooth));
    arrayList2.add(contextc.getResources().getDrawable(appTheme.equals(AppTheme.LIGHT) ? R.drawable.ic_settings_bluetooth_black_24dp : R.drawable.ic_settings_bluetooth_white_36dp));
  }
  return null;
}
