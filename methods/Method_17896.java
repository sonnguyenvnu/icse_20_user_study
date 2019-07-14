/** 
 * Litho handles adding/removing views automatically using mount/unmount calls. Manually adding/ removing views will mess up Litho's bookkeeping of added views and cause weird crashes down the line.
 */
@Deprecated @Override protected void removeDetachedView(View child,boolean animate){
  throw new UnsupportedOperationException("Removing Views manually within LithoViews is not supported");
}
