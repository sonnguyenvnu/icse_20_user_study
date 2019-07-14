protected void onItemRangeInserted(int positionStart,int itemCount){
  for (int position=positionStart, positionEnd=positionStart + itemCount; position < positionEnd; ++position) {
    addItemView(position);
  }
}
