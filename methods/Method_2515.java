/** 
 * ??????????????
 * @param size ??
 * @param value ?
 */
void resize(int size,byte value){
  if (size > _capacity) {
    resizeBuf(size);
  }
  while (_size < size) {
    _buf[_size++]=value;
  }
}
