private int findSameItem(T item,T[] items,int from,int to){
  for (int pos=from; pos < to; pos++) {
    if (mCallback.areItemsTheSame(items[pos],item)) {
      return pos;
    }
  }
  return INVALID_POSITION;
}
