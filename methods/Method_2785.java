/** 
 * ????int
 * @return
 */
public int nextInt(){
  int result=ByteUtil.bytesHighFirstToInt(bytes,offset);
  offset+=4;
  return result;
}
