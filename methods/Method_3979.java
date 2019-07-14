/** 
 * This handles the case where there is an unexpected VH missing in the pre-layout map. <p> We might be able to detect the error in the application which will help the developer to resolve the issue. <p> If it is not an expected error, we at least print an error to notify the developer and ignore the animation. https://code.google.com/p/android/issues/detail?id=193958
 * @param key The change key
 * @param holder Current ViewHolder
 * @param oldChangeViewHolder Changed ViewHolder
 */
private void handleMissingPreInfoForChangeError(long key,ViewHolder holder,ViewHolder oldChangeViewHolder){
  final int childCount=mChildHelper.getChildCount();
  for (int i=0; i < childCount; i++) {
    View view=mChildHelper.getChildAt(i);
    ViewHolder other=getChildViewHolderInt(view);
    if (other == holder) {
      continue;
    }
    final long otherKey=getChangedHolderKey(other);
    if (otherKey == key) {
      if (mAdapter != null && mAdapter.hasStableIds()) {
        throw new IllegalStateException("Two different ViewHolders have the same stable" + " ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT" + " change.\n ViewHolder 1:" + other + " \n View Holder 2:" + holder + exceptionLabel());
      }
 else {
        throw new IllegalStateException("Two different ViewHolders have the same change" + " ID. This might happen due to inconsistent Adapter update events or" + " if the LayoutManager lays out the same View multiple times." + "\n ViewHolder 1:" + other + " \n View Holder 2:" + holder + exceptionLabel());
      }
    }
  }
  Log.e(TAG,"Problem while matching changed view holders with the new" + "ones. The pre-layout information for the change holder " + oldChangeViewHolder + " cannot be found but it is necessary for " + holder + exceptionLabel());
}
