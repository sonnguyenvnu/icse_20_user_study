/** 
 * ??????????
 * @return
 */
public int nextUnsignedShort(){
  byte a=nextByte();
  byte b=nextByte();
  return (((a & 0xff) << 8) | (b & 0xff));
}
