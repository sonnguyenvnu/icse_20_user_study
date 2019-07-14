/** 
 * Returns the type of the NAL unit in  {@code data} that starts at {@code offset}.
 * @param data The data to search.
 * @param offset The start offset of a NAL unit. Must lie between {@code -3} (inclusive) and{@code data.length - 3} (exclusive).
 * @return The type of the unit.
 */
public static int getNalUnitType(byte[] data,int offset){
  return data[offset + 3] & 0x1F;
}
