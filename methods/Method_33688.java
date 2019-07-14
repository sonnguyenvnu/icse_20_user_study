/** 
 * ??????item??
 */
private void moveToCenter(int position){
  View childAt=bindingView.xrvNavi.getChildAt(position - layoutManager.findFirstVisibleItemPosition());
  if (childAt != null) {
    int y=(childAt.getTop() - bindingView.xrvNavi.getHeight() / 2);
    bindingView.xrvNavi.smoothScrollBy(0,y);
  }
}
