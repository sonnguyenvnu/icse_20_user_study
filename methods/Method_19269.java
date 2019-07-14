/** 
 * Update the item at index position. The  {@link RecyclerView} will only be notified of the itembeing updated after a layout calculation has been completed for the new  {@link Component}.
 */
public final void updateItemAtAsync(int position,RenderInfo renderInfo){
  assertSingleThreadForChangeSet();
  if (SectionsDebug.ENABLED) {
    Log.d(SectionsDebug.TAG,"(" + hashCode() + ") updateItemAtAsync " + position);
  }
synchronized (this) {
    addToCurrentBatch(new AsyncUpdateOperation(position,renderInfo));
  }
}
