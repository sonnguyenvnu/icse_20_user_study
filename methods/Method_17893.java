/** 
 * Litho handles adding/removing views automatically using mount/unmount calls. Manually adding/ removing views will mess up Litho's bookkeeping of added views and cause weird crashes down the line.
 */
@Deprecated @Override protected void attachViewToParent(View child,int index,ViewGroup.LayoutParams params){
  throw new UnsupportedOperationException("Adding Views manually within LithoViews is not supported");
}
