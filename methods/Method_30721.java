protected void onItemRangeMoved(int fromPosition,int toPosition,int itemCount){
  View[] itemViews=new View[itemCount];
  for (int i=0; i < itemCount; ++i) {
    itemViews[i]=getChildAt(fromPosition);
    removeViewAt(fromPosition);
  }
  for (int i=0, position=toPosition; i < itemCount; ++i, ++position) {
    addView(itemViews[i],position);
  }
}
