@ReactMethod public void isAutoDateAndTime(Promise p){
  boolean isAutoDateAndTime;
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
    isAutoDateAndTime=Settings.System.getInt(this.reactContext.getContentResolver(),Settings.System.AUTO_TIME,0) != 0;
  }
 else {
    isAutoDateAndTime=Settings.Global.getInt(this.reactContext.getContentResolver(),Settings.Global.AUTO_TIME,0) != 0;
  }
  p.resolve(isAutoDateAndTime);
}
