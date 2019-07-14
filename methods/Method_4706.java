/** 
 * Returns the reading position in bits.
 */
public int getPosition(){
  return byteOffset * 8 + bitOffset;
}
