/** 
 * Writes <code>annotation</code>. This method must be followed by <code>numMemberValuePairs</code> calls to <code>memberValuePair()</code>.
 * @param typeIndex  <code>type_index</code> in <code>annotation</code>.
 * @param numMemberValuePairs   <code>num_element_value_pairs</code>in <code>annotation</code>.
 */
public void annotation(int typeIndex,int numMemberValuePairs) throws IOException {
  write16bit(typeIndex);
  write16bit(numMemberValuePairs);
}
