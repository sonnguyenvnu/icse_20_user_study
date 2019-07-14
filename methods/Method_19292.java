/** 
 * Removes count items starting from position.
 */
@UiThread public final void removeRangeAt(int position,int count){
  ThreadUtils.assertMainThread();
  assertNoRemoveOperationIfCircular(count);
  if (SectionsDebug.ENABLED) {
    Log.d(SectionsDebug.TAG,"(" + hashCode() + ") removeRangeAt " + position + ", size: " + count);
  }
  final List<ComponentTreeHolder> toRelease=new ArrayList<>();
synchronized (this) {
    for (int i=0; i < count; i++) {
      final ComponentTreeHolder holder=mComponentTreeHolders.remove(position);
      toRelease.add(holder);
    }
  }
  mInternalAdapter.notifyItemRangeRemoved(position,count);
  mViewportManager.setShouldUpdate(mViewportManager.removeAffectsVisibleRange(position,count));
  if (ComponentsConfiguration.isReleaseComponentTreeInRecyclerBinder) {
    postReleaseComponentTreeHolders(toRelease);
  }
}
