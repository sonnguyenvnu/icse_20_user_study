public static void addClassName(Context context,Intent intent,String action){
  PackageManager pm=context.getPackageManager();
  try {
    PackageInfo packageInfo=pm.getPackageInfo(context.getPackageName(),PackageManager.GET_RECEIVERS);
    ActivityInfo[] receivers=packageInfo.receivers;
    for (    ActivityInfo receiver : receivers) {
      if (receiver.taskAffinity.equals(action)) {
        intent.setClassName(receiver.packageName,receiver.name);
      }
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  intent.setPackage(context.getPackageName());
}
