/** 
 * Returns <code>local_variable_table[i].index</code>. This represents the index of the local variable.
 * @param i         the i-th entry.
 */
public int index(int i){
  return ByteArray.readU16bit(info,i * 10 + 10);
}
