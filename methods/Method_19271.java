/** 
 * Inserts an item at position. The  {@link RecyclerView} will only be notified of the item beinginserted after a layout calculation has been completed for the new  {@link Component}.
 */
public final void insertItemAtAsync(int position,RenderInfo renderInfo){
  assertSingleThreadForChangeSet();
  assertNoInsertOperationIfCircular();
  if (SectionsDebug.ENABLED) {
    Log.d(SectionsDebug.TAG,"(" + hashCode() + ") insertItemAtAsync " + position + ", name: " + renderInfo.getName());
  }
  assertNotNullRenderInfo(renderInfo);
  final AsyncInsertOperation operation=createAsyncInsertOperation(position,renderInfo);
synchronized (this) {
    mHasAsyncOperations=true;
    mAsyncComponentTreeHolders.add(position,operation.mHolder);
    registerAsyncInsert(operation);
  }
}
