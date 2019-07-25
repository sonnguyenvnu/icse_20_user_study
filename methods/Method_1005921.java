/** 
 * Manually trigger the Hyperion menu embedded in the current foreground  {@link Activity}
 * @return true if the menu was opened, otherwise false
 */
public static boolean open(){
  requireApplication();
  return AppComponent.Holder.getInstance(application).getPublicControl().open();
}
