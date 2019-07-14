/** 
 * Removes an item from index position.
 */
@UiThread public final void removeItemAt(int position){
  ThreadUtils.assertMainThread();
  assertNoRemoveOperationIfCircular(1);
  if (SectionsDebug.ENABLED) {
    Log.d(SectionsDebug.TAG,"(" + hashCode() + ") removeItemAt " + position);
  }
  final ComponentTreeHolder holder;
synchronized (this) {
    holder=mComponentTreeHolders.remove(position);
  }
  mInternalAdapter.notifyItemRemoved(position);
  mViewportManager.setShouldUpdate(mViewportManager.removeAffectsVisibleRange(position,1));
  if (ComponentsConfiguration.isReleaseComponentTreeInRecyclerBinder) {
    mMainThreadHandler.post(new Runnable(){
      @Override public void run(){
        holder.releaseTree();
      }
    }
);
  }
}
