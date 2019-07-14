public static boolean isMIUI(){
  return !TextUtils.isEmpty(AndroidUtilities.getSystemProperty("ro.miui.ui.version.name"));
}
