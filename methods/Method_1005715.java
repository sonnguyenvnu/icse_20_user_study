/** 
 * Returns a negative Integer, a positive Integer, or zero as the <code>builder</code> has judged the "left-hand" side as less than, greater than, or equal to the "right-hand" side.
 * @return final comparison result as an Integer
 * @see #toComparison()
 * @since 3.0
 */
public Integer build(){
  return Integer.valueOf(toComparison());
}
