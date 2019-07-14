/** 
 * Update the items starting from the given index position. The  {@link RecyclerView} will only benotified of the item being updated after a layout calculation has been completed for the new {@link Component}.
 */
public final void updateRangeAtAsync(int position,List<RenderInfo> renderInfos){
  assertSingleThreadForChangeSet();
  if (SectionsDebug.ENABLED) {
    Log.d(SectionsDebug.TAG,"(" + hashCode() + ") updateRangeAtAsync " + position + ", count: " + renderInfos.size());
  }
synchronized (this) {
    addToCurrentBatch(new AsyncUpdateRangeOperation(position,renderInfos));
  }
}
