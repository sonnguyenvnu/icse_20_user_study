/** 
 * ??? <p>?????  {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>}</p>
 * @param crashDir ????????
 */
public static void init(final String crashDir){
  if (isSpace(crashDir)) {
    dir=null;
  }
 else {
    dir=crashDir.endsWith(FILE_SEP) ? crashDir : crashDir + FILE_SEP;
  }
  try {
    PackageManager packageManager=mContext.getPackageManager();
    PackageInfo packageInfo=packageManager.getPackageInfo(mContext.getPackageName(),0);
    int labelRes=packageInfo.applicationInfo.labelRes;
    String name=mContext.getResources().getString(labelRes);
    mCrashDirPath=RxFileTool.getRootPath() + File.separator + name + File.separator + "crash" + File.separator;
  }
 catch (  Exception e) {
    if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
      mCrashDirPath=mContext.getExternalCacheDir().getPath() + File.separator + "crash" + File.separator;
    }
 else {
      mCrashDirPath=mContext.getCacheDir().getPath() + File.separator + "crash" + File.separator;
    }
  }
  Thread.setDefaultUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
}
