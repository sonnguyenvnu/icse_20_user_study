/** 
 * get the bytes for storage
 * @return    byte[]
 */
public byte[] getBytes(){
  byte[] b=new byte[12];
  Util.writeIntLong(b,0,startIp);
  Util.writeIntLong(b,4,endIp);
  long mix=dataPtr | ((dataLen << 24) & 0xFF000000L);
  Util.writeIntLong(b,8,mix);
  return b;
}
