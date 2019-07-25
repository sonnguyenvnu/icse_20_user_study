/** 
 * Returns the type of the next value to read.
 */
public int peek(){
  if (type == MUST_READ) {
    int argAndType=in.readByte() & 0xff;
    type=argAndType & 0x1f;
    arg=(argAndType & 0xe0) >> 5;
  }
  return type;
}
