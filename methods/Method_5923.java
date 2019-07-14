/** 
 * Returns the number of bits yet to be read.
 */
public int bitsLeft(){
  return (byteLimit - byteOffset) * 8 - bitOffset;
}
