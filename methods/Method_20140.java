/** 
 * Set the current list of models. The diff callbacks will be notified of the changes between the current list and the last list that was set.
 */
void notifyModelChanges(){
  UpdateOpHelper updateOpHelper=new UpdateOpHelper();
  buildDiff(updateOpHelper);
  adapter.unregisterAdapterDataObserver(observer);
  notifyChanges(updateOpHelper);
  adapter.registerAdapterDataObserver(observer);
}
