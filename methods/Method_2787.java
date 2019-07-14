/** 
 * ????char????writeChar
 * @return
 */
public char nextChar(){
  char result=ByteUtil.bytesHighFirstToChar(bytes,offset);
  offset+=2;
  return result;
}
