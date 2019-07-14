private void updateSections(ViewGroup listView,boolean checkBottom){
  int count=listView.getChildCount();
  int minPositionDateHolder=Integer.MAX_VALUE;
  View minDateChild=null;
  float padding=listView.getPaddingTop() + actionBar.getTranslationY();
  int maxBottom=0;
  for (int a=0; a < count; a++) {
    View view=listView.getChildAt(a);
    int bottom=view.getBottom();
    maxBottom=Math.max(bottom,maxBottom);
    if (bottom <= padding) {
      continue;
    }
    int position=view.getBottom();
    if (view instanceof SharedMediaSectionCell || view instanceof GraySectionCell) {
      if (view.getAlpha() != 1.0f) {
        view.setAlpha(1.0f);
      }
      if (position < minPositionDateHolder) {
        minPositionDateHolder=position;
        minDateChild=view;
      }
    }
  }
  if (minDateChild != null) {
    if (minDateChild.getTop() > padding) {
      if (minDateChild.getAlpha() != 1.0f) {
        minDateChild.setAlpha(1.0f);
      }
    }
 else {
      if (minDateChild.getAlpha() != 0.0f) {
        minDateChild.setAlpha(0.0f);
      }
    }
  }
  if (checkBottom && maxBottom != 0 && maxBottom < (listView.getMeasuredHeight() - listView.getPaddingBottom())) {
    resetScroll();
  }
}
