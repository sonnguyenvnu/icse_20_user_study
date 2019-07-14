/** 
 * ?? MIUI V6  ???????
 */
private static void setMIUIStatusBarDarkIcon(@NonNull Activity activity,boolean darkIcon){
  Class<? extends Window> clazz=activity.getWindow().getClass();
  try {
    Class<?> layoutParams=Class.forName("android.view.MiuiWindowManager$LayoutParams");
    Field field=layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
    int darkModeFlag=field.getInt(layoutParams);
    Method extraFlagField=clazz.getMethod("setExtraFlags",int.class,int.class);
    extraFlagField.invoke(activity.getWindow(),darkIcon ? darkModeFlag : 0,darkModeFlag);
  }
 catch (  Exception e) {
  }
}
