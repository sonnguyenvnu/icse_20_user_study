/** 
 * To skip length of the wap value.
 * @param pduDataStream pdu data input stream
 * @param length area size
 * @return the values in this area
 */
protected static int skipWapValue(ByteArrayInputStream pduDataStream,int length){
  assert (null != pduDataStream);
  byte[] area=new byte[length];
  int readLen=pduDataStream.read(area,0,length);
  if (readLen < length) {
    return -1;
  }
 else {
    return readLen;
  }
}
