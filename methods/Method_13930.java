/** 
 * @return the number of edits that remain to be done in the current batch
 */
public int remainingEdits(){
  return scheduled.size() - (globalCursor + batchCursor);
}
