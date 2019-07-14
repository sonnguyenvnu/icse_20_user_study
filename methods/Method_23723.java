/** 
 * Remove an element from the specified index.
 * @webref stringlist:method
 * @brief Remove an element from the specified index
 */
public String remove(int index){
  if (index < 0 || index >= count) {
    throw new ArrayIndexOutOfBoundsException(index);
  }
  String entry=data[index];
  for (int i=index; i < count - 1; i++) {
    data[i]=data[i + 1];
  }
  count--;
  return entry;
}
