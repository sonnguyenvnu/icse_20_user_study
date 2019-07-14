/** 
 * Check for any config change between various callbacks to this method. Make sure to recycle after done
 */
public static boolean isConfigChanged(Resources resources){
  int changedFieldsMask=lastConfiguration.updateFrom(resources.getConfiguration());
  boolean densityChanged=lastDensity != resources.getDisplayMetrics().densityDpi;
  int mode=ActivityInfo.CONFIG_SCREEN_LAYOUT | ActivityInfo.CONFIG_UI_MODE | ActivityInfo.CONFIG_LOCALE;
  return densityChanged || (changedFieldsMask & mode) != 0;
}
