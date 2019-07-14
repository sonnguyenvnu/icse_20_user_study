/** 
 * Ignores and clears the queued futures to the synchronous listeners that are processing events this thread published.
 */
public void ignoreSynchronous(){
  pending.get().clear();
}
