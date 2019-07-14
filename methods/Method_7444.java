@SuppressWarnings("JavaReflectionMemberAccess") @TargetApi(19) public static boolean isCustomPermissionGranted(int permission){
  try {
    AppOpsManager mgr=(AppOpsManager)ApplicationLoader.applicationContext.getSystemService(Context.APP_OPS_SERVICE);
    Method m=AppOpsManager.class.getMethod("checkOpNoThrow",int.class,int.class,String.class);
    int result=(int)m.invoke(mgr,permission,android.os.Process.myUid(),ApplicationLoader.applicationContext.getPackageName());
    return result == AppOpsManager.MODE_ALLOWED;
  }
 catch (  Exception x) {
    FileLog.e(x);
  }
  return true;
}
