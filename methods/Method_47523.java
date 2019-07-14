/** 
 * Sets the root view for this screen.
 * @param rootView the root view for this screen.
 */
public void setRootView(@Nullable BaseRootView rootView){
  this.rootView=rootView;
  activity.setContentView(rootView);
  if (rootView == null)   return;
  rootView.onAttachedToScreen(this);
  invalidateToolbar();
}
