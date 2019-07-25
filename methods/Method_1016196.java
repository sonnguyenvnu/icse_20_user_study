/** 
 * Updates cell for the specified element.
 * @param element element to update
 */
public void update(final T element){
  final int index=indexOf(element);
  if (index != -1) {
    fireContentsChanged(this,index,index);
  }
}
