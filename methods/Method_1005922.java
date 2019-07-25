/** 
 * Manually trigger the Hyperion menu embedded in the given  {@link Activity} to open.
 * @param activity the {@link Activity} containing the menu to open.
 * @return true if the menu was opened, otherwise false
 */
public static boolean open(Activity activity){
  requireApplication();
  return AppComponent.Holder.getInstance(application).getPublicControl().open(activity);
}
