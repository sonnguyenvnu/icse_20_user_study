/** 
 * ???????????????MIUIV6??
 * @param window ???????
 * @param dark   ??????????????????
 * @return boolean ??????true
 */
public static boolean MIUISetStatusBarLightMode(Window window,boolean dark){
  boolean result=false;
  if (window != null) {
    Class clazz=window.getClass();
    try {
      int darkModeFlag=0;
      Class layoutParams=Class.forName("android.view.MiuiWindowManager$LayoutParams");
      Field field=layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
      darkModeFlag=field.getInt(layoutParams);
      Method extraFlagField=clazz.getMethod("setExtraFlags",int.class,int.class);
      if (dark) {
        extraFlagField.invoke(window,darkModeFlag,darkModeFlag);
      }
 else {
        extraFlagField.invoke(window,0,darkModeFlag);
      }
      result=true;
    }
 catch (    Exception e) {
    }
  }
  return result;
}
