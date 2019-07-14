/** 
 * ??????????? Flyme 4.0
 */
private static void setMeizuStatusBarDarkIcon(@NonNull Activity activity,boolean darkIcon){
  try {
    WindowManager.LayoutParams lp=activity.getWindow().getAttributes();
    Field darkFlag=WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
    Field meizuFlags=WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
    darkFlag.setAccessible(true);
    meizuFlags.setAccessible(true);
    int bit=darkFlag.getInt(null);
    int value=meizuFlags.getInt(lp);
    if (darkIcon) {
      value|=bit;
    }
 else {
      value&=~bit;
    }
    meizuFlags.setInt(lp,value);
    activity.getWindow().setAttributes(lp);
  }
 catch (  Exception e) {
  }
}
