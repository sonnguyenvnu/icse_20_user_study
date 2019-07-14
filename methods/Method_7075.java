private static File getNativeLibraryDir(Context context){
  File f=null;
  if (context != null) {
    try {
      f=new File((String)ApplicationInfo.class.getField("nativeLibraryDir").get(context.getApplicationInfo()));
    }
 catch (    Throwable th) {
      th.printStackTrace();
    }
  }
  if (f == null) {
    f=new File(context.getApplicationInfo().dataDir,"lib");
  }
  if (f.isDirectory()) {
    return f;
  }
  return null;
}
