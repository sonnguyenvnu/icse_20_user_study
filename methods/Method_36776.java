private View findFixedChildViewUnder(MotionEvent event){
  final int x=(int)event.getX();
  final int y=(int)event.getY();
  final List<View> fixedViews=getLayoutManager().getFixedViews();
  int count=fixedViews.size();
  for (int i=count - 1; i >= 0; --i) {
    View child=fixedViews.get(i);
    float translationX=child.getTranslationX();
    float translationY=child.getTranslationY();
    if (x >= (float)child.getLeft() + translationX && x <= (float)child.getRight() + translationX && y >= (float)child.getTop() + translationY && y <= (float)child.getBottom() + translationY) {
      return child;
    }
  }
  return null;
}
