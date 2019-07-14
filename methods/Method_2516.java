/** 
 * ????
 * @param size ??
 */
void reserve(int size){
  if (size > _capacity) {
    resizeBuf(size);
  }
}
