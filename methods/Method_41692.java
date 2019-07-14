/** 
 * Returns value at the tail of the queue
 * @return Value at the tail of the queue
 */
public T peek(){
  if (depth() == 0) {
    return null;
  }
  return circularArray[getIndex(getCurrentIndex())].get();
}
