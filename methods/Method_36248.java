static boolean isEmui(){
  return !TextUtils.isEmpty(getSystemProperty("ro.build.version.emui"));
}
