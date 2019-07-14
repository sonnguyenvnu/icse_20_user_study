/** 
 * Parse value length.
 * @param pduDataStream pdu data input stream
 * @return the integer
 */
protected static int parseValueLength(ByteArrayInputStream pduDataStream){
  assert (null != pduDataStream);
  int temp=pduDataStream.read();
  assert (-1 != temp);
  int first=temp & 0xFF;
  if (first <= SHORT_LENGTH_MAX) {
    return first;
  }
 else   if (first == LENGTH_QUOTE) {
    return parseUnsignedInt(pduDataStream);
  }
  throw new RuntimeException("Value length > LENGTH_QUOTE!");
}
