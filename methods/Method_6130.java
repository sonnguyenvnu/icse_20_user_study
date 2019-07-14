public static void checkForCrashes(Activity context){
  try {
    CrashManager.register(context,BuildVars.DEBUG_VERSION ? BuildVars.HOCKEY_APP_HASH_DEBUG : BuildVars.HOCKEY_APP_HASH,new CrashManagerListener(){
      @Override public boolean includeDeviceData(){
        return true;
      }
    }
);
  }
 catch (  Throwable e) {
    FileLog.e(e);
  }
}
