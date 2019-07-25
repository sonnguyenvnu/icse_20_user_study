public static CrashHandler init(Context applicationContext){
  if (mContext == null) {
    mContext=applicationContext;
    CRASH_PATH=mContext.getExternalFilesDir(null) + File.separator + "crash";
  }
  return INSTANCE;
}
