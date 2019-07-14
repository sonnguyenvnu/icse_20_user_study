private boolean appInstalledOrNot(String uri,PackageManager pm){
  boolean app_installed;
  try {
    pm.getPackageInfo(uri,PackageManager.GET_ACTIVITIES);
    app_installed=true;
  }
 catch (  PackageManager.NameNotFoundException e) {
    app_installed=false;
  }
  return app_installed;
}
