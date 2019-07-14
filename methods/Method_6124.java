public static Point getRealScreenSize(){
  Point size=new Point();
  try {
    WindowManager windowManager=(WindowManager)ApplicationLoader.applicationContext.getSystemService(Context.WINDOW_SERVICE);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      windowManager.getDefaultDisplay().getRealSize(size);
    }
 else {
      try {
        Method mGetRawW=Display.class.getMethod("getRawWidth");
        Method mGetRawH=Display.class.getMethod("getRawHeight");
        size.set((Integer)mGetRawW.invoke(windowManager.getDefaultDisplay()),(Integer)mGetRawH.invoke(windowManager.getDefaultDisplay()));
      }
 catch (      Exception e) {
        size.set(windowManager.getDefaultDisplay().getWidth(),windowManager.getDefaultDisplay().getHeight());
        FileLog.e(e);
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return size;
}
