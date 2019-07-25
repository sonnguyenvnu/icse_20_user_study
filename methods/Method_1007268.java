/** 
 * Writes <code>annotation</code>. This method must be followed by <code>numMemberValuePairs</code> calls to <code>memberValuePair()</code>.
 * @param type                  the annotation interface name.
 * @param numMemberValuePairs   <code>num_element_value_pairs</code>in <code>annotation</code>.
 */
public void annotation(String type,int numMemberValuePairs) throws IOException {
  annotation(pool.addUtf8Info(type),numMemberValuePairs);
}
