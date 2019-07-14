/** 
 * Removes all items in this binder async. 
 */
public final void clearAsync(){
  assertSingleThreadForChangeSet();
  if (SectionsDebug.ENABLED) {
    Log.d(SectionsDebug.TAG,"(" + hashCode() + ") clear");
  }
synchronized (this) {
    mHasAsyncOperations=true;
    final int count=mAsyncComponentTreeHolders.size();
    mAsyncComponentTreeHolders.clear();
    final AsyncRemoveRangeOperation operation=new AsyncRemoveRangeOperation(0,count);
    addToCurrentBatch(operation);
  }
}
