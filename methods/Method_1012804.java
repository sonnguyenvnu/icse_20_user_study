/** 
 * Returns an array of column specifications that is built from the given encoded column specifications by flipping each column spec and reversing their order.
 * @param encodedColumnSpecs the original comma-separated encoded column specifications
 * @return an array of flipped column specs in reversed order
 */
public static String flip(String encodedColumnSpecs){
  StringBuilder result=new StringBuilder();
  String separator="";
  ColumnSpec[] flippedSpecs=flipped(ColumnSpec.decodeSpecs(encodedColumnSpecs));
  for (  ColumnSpec spec : flippedSpecs) {
    result.append(separator);
    result.append(spec.encode());
    separator=", ";
  }
  return result.toString();
}
