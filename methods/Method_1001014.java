/** 
 * @return number of bits that can be read successfully
 */
public int available(){
  return 8 * (bytes.length - byteOffset) - bitOffset;
}
