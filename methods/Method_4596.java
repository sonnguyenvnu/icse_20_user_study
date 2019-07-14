/** 
 * Assemble a varint from the given byte array.
 * @param varintBytes Bytes that make up the varint.
 * @param varintLength Length of the varint to assemble.
 * @param removeLengthMask Removes the variable-length integer length mask from the value.
 * @return Parsed and assembled varint.
 */
public static long assembleVarint(byte[] varintBytes,int varintLength,boolean removeLengthMask){
  long varint=varintBytes[0] & 0xFFL;
  if (removeLengthMask) {
    varint&=~VARINT_LENGTH_MASKS[varintLength - 1];
  }
  for (int i=1; i < varintLength; i++) {
    varint=(varint << 8) | (varintBytes[i] & 0xFFL);
  }
  return varint;
}
