public void setup(){
  int itemCount=mItemData.size();
  int childCount=mViews.size();
  int i;
  if (childCount > itemCount) {
    detach(childCount - itemCount);
  }
 else   if (childCount < itemCount) {
    for (i=0; i < itemCount - childCount; i++) {
      V view=getView();
      mParentView.addView(view);
      mViews.add(view);
    }
  }
  for (i=0; i < itemCount; i++) {
    V view=mViews.get(i);
    T item=mItemData.get(i);
    bind(item,view,i);
  }
  mParentView.invalidate();
  mParentView.requestLayout();
}
