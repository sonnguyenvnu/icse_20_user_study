/** 
 * Manually trigger closing the Hyperion menu embedded in the current foreground  {@link Activity}
 * @return true if the menu was closed, otherwise false
 */
public static boolean close(){
  requireApplication();
  return AppComponent.Holder.getInstance(application).getPublicControl().close();
}
