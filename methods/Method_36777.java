private View findScrollableChildViewUnder(MotionEvent event){
  final int x=(int)event.getX();
  final int y=(int)event.getY();
  final int first=getLayoutManager().findFirstVisibleItemPosition();
  final int last=getLayoutManager().findLastVisibleItemPosition();
  for (int i=0; i <= last - first; i++) {
    View child=getLayoutManager().getChildAt(i);
    if (child instanceof ViewGroup) {
      float translationX=child.getTranslationX();
      float translationY=child.getTranslationY();
      if (x >= (float)child.getLeft() + translationX && x <= (float)child.getRight() + translationX && y >= (float)child.getTop() + translationY && y <= (float)child.getBottom() + translationY) {
        if (findCanScrollView(child) != null) {
          return child;
        }
      }
    }
  }
  return null;
}
