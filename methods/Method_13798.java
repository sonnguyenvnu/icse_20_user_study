/** 
 * get the region with a int ip address with b-tree algorithm
 * @param ip
 * @throws IOException 
 */
public DataBlock btreeSearch(long ip) throws IOException {
  if (HeaderSip == null) {
    raf.seek(8L);
    byte[] b=new byte[dbConfig.getTotalHeaderSize()];
    raf.readFully(b,0,b.length);
    int len=b.length >> 3, idx=0;
    HeaderSip=new long[len];
    HeaderPtr=new int[len];
    long startIp, dataPtr;
    for (int i=0; i < b.length; i+=8) {
      startIp=Util.getIntLong(b,i);
      dataPtr=Util.getIntLong(b,i + 4);
      if (dataPtr == 0)       break;
      HeaderSip[idx]=startIp;
      HeaderPtr[idx]=(int)dataPtr;
      idx++;
    }
    headerLength=idx;
  }
  if (ip == HeaderSip[0]) {
    return getByIndexPtr(HeaderPtr[0]);
  }
 else   if (ip == HeaderSip[headerLength - 1]) {
    return getByIndexPtr(HeaderPtr[headerLength - 1]);
  }
  int l=0, h=headerLength, sptr=0, eptr=0;
  while (l <= h) {
    int m=(l + h) >> 1;
    if (ip == HeaderSip[m]) {
      if (m > 0) {
        sptr=HeaderPtr[m - 1];
        eptr=HeaderPtr[m];
      }
 else {
        sptr=HeaderPtr[m];
        eptr=HeaderPtr[m + 1];
      }
      break;
    }
    if (ip < HeaderSip[m]) {
      if (m == 0) {
        sptr=HeaderPtr[m];
        eptr=HeaderPtr[m + 1];
        break;
      }
 else       if (ip > HeaderSip[m - 1]) {
        sptr=HeaderPtr[m - 1];
        eptr=HeaderPtr[m];
        break;
      }
      h=m - 1;
    }
 else {
      if (m == headerLength - 1) {
        sptr=HeaderPtr[m - 1];
        eptr=HeaderPtr[m];
        break;
      }
 else       if (ip <= HeaderSip[m + 1]) {
        sptr=HeaderPtr[m];
        eptr=HeaderPtr[m + 1];
        break;
      }
      l=m + 1;
    }
  }
  if (sptr == 0)   return null;
  int blockLen=eptr - sptr, blen=IndexBlock.getIndexBlockLength();
  byte[] iBuffer=new byte[blockLen + blen];
  raf.seek(sptr);
  raf.readFully(iBuffer,0,iBuffer.length);
  l=0;
  h=blockLen / blen;
  long sip, eip, dataptr=0;
  while (l <= h) {
    int m=(l + h) >> 1;
    int p=m * blen;
    sip=Util.getIntLong(iBuffer,p);
    if (ip < sip) {
      h=m - 1;
    }
 else {
      eip=Util.getIntLong(iBuffer,p + 4);
      if (ip > eip) {
        l=m + 1;
      }
 else {
        dataptr=Util.getIntLong(iBuffer,p + 8);
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
