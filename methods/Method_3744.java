/** 
 * Moves a child view from hidden list to regular list. Calling this method should probably be followed by a detach, otherwise, it will suddenly show up in LayoutManager's children list.
 * @param view The hidden View to unhide
 */
void unhide(View view){
  final int offset=mCallback.indexOfChild(view);
  if (offset < 0) {
    throw new IllegalArgumentException("view is not a child, cannot hide " + view);
  }
  if (!mBucket.get(offset)) {
    throw new RuntimeException("trying to unhide a view that was not hidden" + view);
  }
  mBucket.clear(offset);
  unhideViewInternal(view);
}
