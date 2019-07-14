public static boolean needsLegacyHtml(){
  return android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.N;
}
