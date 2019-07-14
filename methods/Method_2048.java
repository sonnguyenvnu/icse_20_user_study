/** 
 * Update the LayoutParams of the given View
 * @param view   The View to layout
 * @param width  The wanted width
 * @param height The wanted height
 */
public static void updateViewLayoutParams(View view,int width,int height){
  ViewGroup.LayoutParams layoutParams=view.getLayoutParams();
  if (layoutParams == null || layoutParams.height != width || layoutParams.width != height) {
    layoutParams=new AbsListView.LayoutParams(width,height);
    view.setLayoutParams(layoutParams);
  }
}
