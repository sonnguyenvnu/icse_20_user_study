/** 
 * Extract a byte value from the input stream.
 * @param pduDataStream pdu data input stream
 * @return the byte
 */
protected static int extractByteValue(ByteArrayInputStream pduDataStream){
  assert (null != pduDataStream);
  int temp=pduDataStream.read();
  assert (-1 != temp);
  return temp & 0xFF;
}
