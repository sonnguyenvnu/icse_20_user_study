/** 
 * Should be called before super.onConfigurationChanged() to avoid activity recreation by AppCompat.
 * @see androidx.appcompat.app.AppCompatDelegateImpl#updateForNightMode(int)
 */
public static void onConfigurationChanged(@NonNull Activity activity,@NonNull TriConsumer<Resources.Theme,Integer,Boolean> onApplyThemeResource,@StyleRes int themeRes){
  boolean isInNightMode=sActivityHelper.isActivityInNightMode(activity);
  int uiModeNight=isInNightMode ? Configuration.UI_MODE_NIGHT_YES : Configuration.UI_MODE_NIGHT_NO;
  Resources resources=activity.getResources();
  Configuration newConfiguration=new Configuration(resources.getConfiguration());
  newConfiguration.uiMode=(newConfiguration.uiMode & ~Configuration.UI_MODE_NIGHT_MASK) | uiModeNight;
  resources.updateConfiguration(newConfiguration,resources.getDisplayMetrics());
  ResourcesFlusher.flush(resources);
  onApplyThemeResource.accept(activity.getTheme(),themeRes,true);
  sActivityHelper.onActivityCreated(activity,null);
}
