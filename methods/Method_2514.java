/** 
 * ????
 * @param size ??
 */
void resize(int size){
  if (size > _capacity) {
    resizeBuf(size);
  }
  _size=size;
}
