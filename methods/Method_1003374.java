/** 
 * Get or create a UUID for the given 16 bytes.
 * @param binary the byte array (must be at least 16 bytes long)
 * @return the UUID
 */
public static ValueUuid get(byte[] binary){
  if (binary.length < 16) {
    return get(StringUtils.convertBytesToHex(binary));
  }
  long high=Bits.readLong(binary,0);
  long low=Bits.readLong(binary,8);
  return (ValueUuid)Value.cache(new ValueUuid(high,low));
}
