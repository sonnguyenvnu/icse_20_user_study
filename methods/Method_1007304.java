/** 
 * Returns the value of <code>name_index</code> of the i-th element of <code>parameters</code>.
 * @param i         the position of the parameter.
 */
public int name(int i){
  return ByteArray.readU16bit(info,i * 4 + 1);
}
