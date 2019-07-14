public static int selectDefaultTheme(final int curTheme,final int targetSdkVersion){
  return selectSystemTheme(curTheme,targetSdkVersion,android.R.style.Theme,android.R.style.Theme_Holo,android.R.style.Theme_DeviceDefault,android.R.style.Theme_DeviceDefault_Light_DarkActionBar);
}
