/** 
 * Matches if the expression is provably null. 
 */
public static Matcher<ExpressionTree> isNull(){
  return new NullnessMatcher(Nullness.NULL);
}
