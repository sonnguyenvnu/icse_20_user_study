private int getAdapterIndexForViewType(int viewType){
  int mapIndex=mItemViewTypeToAdapterIndexMap.indexOfKey(viewType);
  if (mapIndex < 0) {
    throw new IllegalStateException("Unknown viewType: " + viewType);
  }
  return mItemViewTypeToAdapterIndexMap.valueAt(mapIndex);
}
