/** 
 * Manually trigger the Hyperion menu embedded in the given  {@link Activity} to close.
 * @param activity the {@link Activity} containing the menu to close.
 * @return true if the menu was closed, otherwise false
 */
public static boolean close(Activity activity){
  requireApplication();
  return AppComponent.Holder.getInstance(application).getPublicControl().close(activity);
}
