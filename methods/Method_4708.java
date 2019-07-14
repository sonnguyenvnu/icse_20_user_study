/** 
 * Returns the number of remaining bits.
 */
public int bitsLeft(){
  return (byteLimit - byteOffset) * 8 - bitOffset;
}
