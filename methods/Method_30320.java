private boolean shouldAllowShare(){
  return mItem.collection == null || getState() != mItem.collection.getState();
}
