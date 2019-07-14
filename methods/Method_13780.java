/** 
 * get by index ptr
 * @param indexPtr
 * @throws IOException 
 */
public DataBlock getByIndexPtr(long ptr) throws IOException {
  raf.seek(ptr);
  byte[] buffer=new byte[12];
  raf.readFully(buffer,0,buffer.length);
  long extra=Util.getIntLong(buffer,8);
  int dataLen=(int)((extra >> 24) & 0xFF);
  int dataPtr=(int)((extra & 0x00FFFFFF));
  raf.seek(dataPtr);
  byte[] data=new byte[dataLen];
  raf.readFully(data,0,data.length);
  int city_id=(int)Util.getIntLong(data,0);
  String region=new String(data,4,data.length - 4,"UTF-8");
  return new DataBlock(city_id,region,dataPtr);
}
