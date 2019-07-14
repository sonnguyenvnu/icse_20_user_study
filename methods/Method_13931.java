/** 
 * @return the progress, measured as a percentage
 */
public int progress(){
  return (100 * (globalCursor + batchCursor)) / scheduled.size();
}
