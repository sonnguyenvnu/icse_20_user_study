/** 
 * Returns an array of column specifications that is built from the given encoded column specifications by flipping each column spec and reversing their order.
 * @param encodedColumnSpecs the original comma-separated encoded column specifications
 * @return an array of flipped column specs in reversed order
 */
@SuppressWarnings("unused") private static ColumnSpec[] flipped(String encodedColumnSpecs){
  return flipped(ColumnSpec.decodeSpecs(encodedColumnSpecs));
}
