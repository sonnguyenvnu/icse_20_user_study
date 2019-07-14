/** 
 * get the region with a int ip address with binary search algorithm
 * @param ip
 * @throws IOException 
 */
public DataBlock binarySearch(long ip) throws IOException {
  int blen=IndexBlock.getIndexBlockLength();
  if (totalIndexBlocks == 0) {
    raf.seek(0L);
    byte[] superBytes=new byte[8];
    raf.readFully(superBytes,0,superBytes.length);
    firstIndexPtr=Util.getIntLong(superBytes,0);
    lastIndexPtr=Util.getIntLong(superBytes,4);
    totalIndexBlocks=(int)((lastIndexPtr - firstIndexPtr) / blen) + 1;
  }
  int l=0, h=totalIndexBlocks;
  byte[] buffer=new byte[blen];
  long sip, eip, dataptr=0;
  while (l <= h) {
    int m=(l + h) >> 1;
    raf.seek(firstIndexPtr + m * blen);
    raf.readFully(buffer,0,buffer.length);
    sip=Util.getIntLong(buffer,0);
    if (ip < sip) {
      h=m - 1;
    }
 else {
      eip=Util.getIntLong(buffer,4);
      if (ip > eip) {
        l=m + 1;
      }
 else {
        dataptr=Util.getIntLong(buffer,8);
        break;
      }
    }
  }
  if (dataptr == 0)   return null;
  int dataLen=(int)((dataptr >> 24) & 0xFF);
  int dataPtr=(int)((dataptr & 0x00FFFFFF));
  raf.seek(dataPtr);
  byte[] data=new byte[dataLen];
  raf.readFully(data,0,data.length);
  int city_id=(int)Util.getIntLong(data,0);
  String region=new String(data,4,data.length - 4,"UTF-8");
  return new DataBlock(city_id,region,dataPtr);
}
