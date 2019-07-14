/** 
 * Mounts a drawable into a view.
 * @param view view into which the drawable should be mounted
 * @param drawable drawable to be mounted
 * @param bounds bounds of the drawable being mounted
 * @param flags flags that determine whether the drawable obtains state from the view
 * @param nodeInfo nodeInfo associated to the drawable node
 */
static void mountDrawable(View view,Drawable drawable,Rect bounds,int flags,NodeInfo nodeInfo){
  drawable.setVisible(view.getVisibility() == View.VISIBLE,false);
  drawable.setCallback(view);
  maybeSetDrawableState(view,drawable,flags,nodeInfo);
  view.invalidate(bounds);
}
