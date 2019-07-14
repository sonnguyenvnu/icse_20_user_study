/** 
 * Moves an item from fromPosition to toPostion. If there are other pending operations on this binder this will only be executed when all the operations have been completed (to ensure index consistency).
 */
public final void moveItemAsync(int fromPosition,int toPosition){
  assertSingleThreadForChangeSet();
  if (SectionsDebug.ENABLED) {
    Log.d(SectionsDebug.TAG,"(" + hashCode() + ") moveItemAsync " + fromPosition + " to " + toPosition);
  }
  final AsyncMoveOperation operation=new AsyncMoveOperation(fromPosition,toPosition);
synchronized (this) {
    mHasAsyncOperations=true;
    mAsyncComponentTreeHolders.add(toPosition,mAsyncComponentTreeHolders.remove(fromPosition));
    addToCurrentBatch(operation);
  }
}
