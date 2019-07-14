/** 
 * ???????
 * @param value ?
 */
void add(byte value){
  if (_size == _capacity) {
    resizeBuf(_size + 1);
  }
  _buf[_size++]=value;
}
