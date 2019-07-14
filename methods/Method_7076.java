@SuppressLint("UnsafeDynamicallyLoadedCode") public static synchronized void initNativeLibs(Context context){
  if (nativeLoaded) {
    return;
  }
  Constants.loadFromContext(context);
  try {
    try {
      System.loadLibrary(LIB_NAME);
      nativeLoaded=true;
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("loaded normal lib");
      }
      return;
    }
 catch (    Error e) {
      FileLog.e(e);
    }
    String folder;
    try {
      String str=Build.CPU_ABI;
      if (Build.CPU_ABI.equalsIgnoreCase("x86_64")) {
        folder="x86_64";
      }
 else       if (Build.CPU_ABI.equalsIgnoreCase("arm64-v8a")) {
        folder="arm64-v8a";
      }
 else       if (Build.CPU_ABI.equalsIgnoreCase("armeabi-v7a")) {
        folder="armeabi-v7a";
      }
 else       if (Build.CPU_ABI.equalsIgnoreCase("armeabi")) {
        folder="armeabi";
      }
 else       if (Build.CPU_ABI.equalsIgnoreCase("x86")) {
        folder="x86";
      }
 else       if (Build.CPU_ABI.equalsIgnoreCase("mips")) {
        folder="mips";
      }
 else {
        folder="armeabi";
        if (BuildVars.LOGS_ENABLED) {
          FileLog.e("Unsupported arch: " + Build.CPU_ABI);
        }
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
      folder="armeabi";
    }
    String javaArch=System.getProperty("os.arch");
    if (javaArch != null && javaArch.contains("686")) {
      folder="x86";
    }
    File destDir=new File(context.getFilesDir(),"lib");
    destDir.mkdirs();
    File destLocalFile=new File(destDir,LOCALE_LIB_SO_NAME);
    if (destLocalFile.exists()) {
      try {
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("Load local lib");
        }
        System.load(destLocalFile.getAbsolutePath());
        nativeLoaded=true;
        return;
      }
 catch (      Error e) {
        FileLog.e(e);
      }
      destLocalFile.delete();
    }
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("Library not found, arch = " + folder);
    }
    if (loadFromZip(context,destDir,destLocalFile,folder)) {
      return;
    }
  }
 catch (  Throwable e) {
    e.printStackTrace();
  }
  try {
    System.loadLibrary(LIB_NAME);
    nativeLoaded=true;
  }
 catch (  Error e) {
    FileLog.e(e);
  }
}
