/** 
 * Matches if the expression is provably non-null. 
 */
public static Matcher<ExpressionTree> isNonNull(){
  return new NullnessMatcher(Nullness.NONNULL);
}
