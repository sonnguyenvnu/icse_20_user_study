@ReactMethod public void isAutoTimeZone(Promise p){
  boolean isAutoTimeZone;
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
    isAutoTimeZone=Settings.System.getInt(this.reactContext.getContentResolver(),Settings.System.AUTO_TIME_ZONE,0) != 0;
  }
 else {
    isAutoTimeZone=Settings.Global.getInt(this.reactContext.getContentResolver(),Settings.Global.AUTO_TIME_ZONE,0) != 0;
  }
  p.resolve(isAutoTimeZone);
}
