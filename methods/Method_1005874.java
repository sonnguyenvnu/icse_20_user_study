/** 
 * Pushes an integer value to the next available spot in the Array. In particular, this[length] = value;
 * @param value The value to push to the array.
 * @return The receiver.
 */
public V8Array push(final int value){
  v8.checkThread();
  checkReleased();
  v8.addArrayIntItem(v8.getV8RuntimePtr(),getHandle(),value);
  return this;
}
