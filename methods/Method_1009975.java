/** 
 * Resize internal storage in order to be able to store data for nodes up to <code>newCapacity</code> (excluded).
 */
protected void resize(int newCapacity){
  parent=Arrays.copyOf(parent,newCapacity);
  left=Arrays.copyOf(left,newCapacity);
  right=Arrays.copyOf(right,newCapacity);
  depth=Arrays.copyOf(depth,newCapacity);
}
