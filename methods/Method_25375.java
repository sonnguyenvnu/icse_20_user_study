/** 
 * Matches a compound assignment operator AST node which matches a given left-operand matcher, a given right-operand matcher, and a specific compound assignment operator.
 * @param operator Which compound assignment operator to match against.
 * @param leftOperandMatcher The matcher to apply to the left operand.
 * @param rightOperandMatcher The matcher to apply to the right operand.
 */
public static CompoundAssignment compoundAssignment(Kind operator,Matcher<ExpressionTree> leftOperandMatcher,Matcher<ExpressionTree> rightOperandMatcher){
  Set<Kind> operators=new HashSet<>(1);
  operators.add(operator);
  return new CompoundAssignment(operators,leftOperandMatcher,rightOperandMatcher);
}
