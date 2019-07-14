/** 
 * ???????????????????? ?????????Flyme??
 * @param window ???????
 * @param dark   ??????????????????
 * @return boolean ??????true
 */
public static boolean FlymeSetStatusBarLightMode(Window window,boolean dark){
  boolean result=false;
  if (window != null) {
    try {
      WindowManager.LayoutParams lp=window.getAttributes();
      Field darkFlag=WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
      Field meizuFlags=WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
      darkFlag.setAccessible(true);
      meizuFlags.setAccessible(true);
      int bit=darkFlag.getInt(null);
      int value=meizuFlags.getInt(lp);
      if (dark) {
        value|=bit;
      }
 else {
        value&=~bit;
      }
      meizuFlags.setInt(lp,value);
      window.setAttributes(lp);
      result=true;
    }
 catch (    Exception e) {
    }
  }
  return result;
}
