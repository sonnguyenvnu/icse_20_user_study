@SuppressWarnings("WeakerAccess") void removeChildDrawingOrderCallbackIfNecessary(View view){
  if (view == mOverdrawChild) {
    mOverdrawChild=null;
    if (mChildDrawingOrderCallback != null) {
      mRecyclerView.setChildDrawingOrderCallback(null);
    }
  }
}
