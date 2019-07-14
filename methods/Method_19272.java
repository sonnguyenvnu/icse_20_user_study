/** 
 * Inserts the new items starting from position. The  {@link RecyclerView} will only be notified ofthe items being inserted after a layout calculation has been completed for the new  {@link Component}s. There is not a guarantee that the  {@link RecyclerView} will be notified about allthe items in the range at the same time.
 */
public final void insertRangeAtAsync(int position,List<RenderInfo> renderInfos){
  assertSingleThreadForChangeSet();
  assertNoInsertOperationIfCircular();
  if (SectionsDebug.ENABLED) {
    final String[] names=new String[renderInfos.size()];
    for (int i=0; i < renderInfos.size(); i++) {
      names[i]=renderInfos.get(i).getName();
    }
    Log.d(SectionsDebug.TAG,"(" + hashCode() + ") insertRangeAtAsync " + position + ", size: " + renderInfos.size() + ", names: " + Arrays.toString(names));
  }
synchronized (this) {
    mHasAsyncOperations=true;
    for (int i=0, size=renderInfos.size(); i < size; i++) {
      final RenderInfo renderInfo=renderInfos.get(i);
      assertNotNullRenderInfo(renderInfo);
      final AsyncInsertOperation operation=createAsyncInsertOperation(position + i,renderInfo);
      mAsyncComponentTreeHolders.add(position + i,operation.mHolder);
      registerAsyncInsert(operation);
    }
  }
}
