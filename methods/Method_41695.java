/** 
 * Returns the number of items currently in the queue
 * @return the number of items in the queue
 */
public int depth(){
  long currInd=currentIndex.get() + 1;
  return currInd >= maxSize ? maxSize : (int)currInd;
}
