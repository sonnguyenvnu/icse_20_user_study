/** 
 * get the bytes for db storage
 * @return    byte[]
 */
public byte[] getBytes(){
  byte[] b=new byte[8];
  Util.writeIntLong(b,0,indexStartIp);
  Util.writeIntLong(b,4,indexPtr);
  return b;
}
