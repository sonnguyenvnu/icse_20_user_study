/** 
 * Sets the reading position in bits.
 * @param position The new reading position in bits.
 */
public void setPosition(int position){
  byteOffset=position / 8;
  bitOffset=position - (byteOffset * 8);
  assertValidOffset();
}
