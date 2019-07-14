/** 
 * Parse Integer-Value.
 * @param pduDataStream pdu data input stream
 * @return long integer
 */
protected static long parseIntegerValue(ByteArrayInputStream pduDataStream){
  assert (null != pduDataStream);
  pduDataStream.mark(1);
  int temp=pduDataStream.read();
  assert (-1 != temp);
  pduDataStream.reset();
  if (temp > SHORT_INTEGER_MAX) {
    return parseShortInteger(pduDataStream);
  }
 else {
    return parseLongInteger(pduDataStream);
  }
}
