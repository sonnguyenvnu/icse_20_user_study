protected void onItemRangeChanged(int positionStart,int itemCount){
  for (int position=positionStart, positionEnd=positionStart + itemCount; position < positionEnd; ++position) {
    updateItemView(position);
  }
}
