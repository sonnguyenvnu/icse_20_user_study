/** 
 * ???????
 * @param size ??
 */
private void resizeBuf(int size){
  int capacity;
  if (size >= _capacity * 2) {
    capacity=size;
  }
 else {
    capacity=1;
    while (capacity < size) {
      capacity<<=1;
    }
  }
  byte[] buf=new byte[capacity];
  if (_size > 0) {
    System.arraycopy(_buf,0,buf,0,_size);
  }
  _buf=buf;
  _capacity=capacity;
}
