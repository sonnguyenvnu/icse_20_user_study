public static boolean existSDcard(){
  String externalStorageState;
  try {
    externalStorageState=Environment.getExternalStorageState();
  }
 catch (  NullPointerException e) {
    externalStorageState="";
  }
  return MEDIA_MOUNTED.equals(externalStorageState);
}
