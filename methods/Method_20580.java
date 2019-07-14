public String variant(){
  return new StringBuilder().append(BuildConfig.FLAVOR).append(BuildConfig.BUILD_TYPE.substring(0,1).toUpperCase(Locale.US)).append(BuildConfig.BUILD_TYPE.substring(1)).toString();
}
