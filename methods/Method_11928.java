/** 
 * Returns a  {@linkplain TestRule rule} that expects no exception tobe thrown (identical to behavior without this rule).
 */
public static ExpectedException none(){
  return new ExpectedException();
}
