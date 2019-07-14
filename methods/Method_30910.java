public static boolean isEmotionUi(){
  return getBuildProperties().containsKey("ro.build.version.emui");
}
