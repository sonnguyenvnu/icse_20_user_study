/** 
 * Parse Short-Integer.
 * @param pduDataStream pdu data input stream
 * @return the byte
 */
protected static int parseShortInteger(ByteArrayInputStream pduDataStream){
  assert (null != pduDataStream);
  int temp=pduDataStream.read();
  assert (-1 != temp);
  return temp & 0x7F;
}
