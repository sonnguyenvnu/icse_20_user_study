@ReactMethod public void isAirPlaneMode(Promise p){
  boolean isAirPlaneMode;
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
    isAirPlaneMode=Settings.System.getInt(this.reactContext.getContentResolver(),Settings.System.AIRPLANE_MODE_ON,0) != 0;
  }
 else {
    isAirPlaneMode=Settings.Global.getInt(this.reactContext.getContentResolver(),Settings.Global.AIRPLANE_MODE_ON,0) != 0;
  }
  p.resolve(isAirPlaneMode);
}
