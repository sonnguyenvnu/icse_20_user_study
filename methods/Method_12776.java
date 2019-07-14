public static void copyNativeLib(File apk,Context context,PackageInfo packageInfo,File nativeLibDir) throws Exception {
  long startTime=System.currentTimeMillis();
  ZipFile zipfile=new ZipFile(apk.getAbsolutePath());
  try {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      for (      String cpuArch : Build.SUPPORTED_ABIS) {
        if (findAndCopyNativeLib(zipfile,context,cpuArch,packageInfo,nativeLibDir)) {
          return;
        }
      }
    }
 else {
      if (findAndCopyNativeLib(zipfile,context,Build.CPU_ABI,packageInfo,nativeLibDir)) {
        return;
      }
    }
    findAndCopyNativeLib(zipfile,context,"armeabi",packageInfo,nativeLibDir);
  }
  finally {
    zipfile.close();
    Log.d(TAG,"Done! +" + (System.currentTimeMillis() - startTime) + "ms");
  }
}
