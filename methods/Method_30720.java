protected void onItemRangeRemoved(int positionStart,int itemCount){
  for (int i=0; i < itemCount; ++i) {
    removeViewAt(positionStart);
  }
}
