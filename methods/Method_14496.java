/** 
 * @return the next available cell index
 */
synchronized public int allocateNewCellIndex(){
  return ++_maxCellIndex;
}
