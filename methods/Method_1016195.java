/** 
 * Removes all of the elements from this list.  The list will be empty after this call returns (unless it throws an exception).
 */
public void clear(){
  final int index1=delegate.size() - 1;
  delegate.removeAllElements();
  if (index1 >= 0) {
    fireIntervalRemoved(this,0,index1);
  }
}
