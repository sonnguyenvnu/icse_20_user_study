public static int selectSystemTheme(final int curTheme,final int targetSdkVersion,final int orig,final int holo,final int dark,final int deviceDefault){
  if (curTheme != 0) {
    return curTheme;
  }
  if (targetSdkVersion < 11) {
    return orig;
  }
  if (targetSdkVersion < 14) {
    return holo;
  }
  if (targetSdkVersion < 24) {
    return dark;
  }
  return deviceDefault;
}
