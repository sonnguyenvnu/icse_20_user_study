/** 
 * get the region with a int ip address with memory binary search algorithm
 * @param ip
 * @throws IOException
 */
public DataBlock memorySearch(long ip) throws IOException {
  int blen=IndexBlock.getIndexBlockLength();
  if (dbBinStr == null) {
    dbBinStr=new byte[(int)raf.length()];
    raf.seek(0L);
    raf.readFully(dbBinStr,0,dbBinStr.length);
    firstIndexPtr=Util.getIntLong(dbBinStr,0);
    lastIndexPtr=Util.getIntLong(dbBinStr,4);
    totalIndexBlocks=(int)((lastIndexPtr - firstIndexPtr) / blen) + 1;
  }
  int l=0, h=totalIndexBlocks;
  long sip, eip, dataptr=0;
  while (l <= h) {
    int m=(l + h) >> 1;
    int p=(int)(firstIndexPtr + m * blen);
    sip=Util.getIntLong(dbBinStr,p);
    if (ip < sip) {
      h=m - 1;
    }
 else {
      eip=Util.getIntLong(dbBinStr,p + 4);
      if (ip > eip) {
        l=m + 1;
      }
 else {
        dataptr=Util.getIntLong(dbBinStr,p + 8);
        break;
      }
    }
  }
  if (dataptr == 0)   return null;
  int dataLen=(int)((dataptr >> 24) & 0xFF);
  int dataPtr=(int)((dataptr & 0x00FFFFFF));
  int city_id=(int)Util.getIntLong(dbBinStr,dataPtr);
  String region=new String(dbBinStr,dataPtr + 4,dataLen - 4,"UTF-8");
  return new DataBlock(city_id,region,dataPtr);
}
