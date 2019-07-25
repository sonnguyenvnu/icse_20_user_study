@Override public Integer call(){
  try {
    Context context=DemoApplication.getContext();
    return context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionCode;
  }
 catch (  PackageManager.NameNotFoundException e) {
    return -1;
  }
}
