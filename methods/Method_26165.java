/** 
 * Matches patterns like i = i + 1 and i = i - 1 in which i is volatile, and the pattern is not enclosed by a synchronized block.
 */
private static Matcher<AssignmentTree> assignmentIncrementDecrementMatcher(ExpressionTree variable){
  return allOf(variableFromAssignmentTree(Matchers.<ExpressionTree>hasModifier(Modifier.VOLATILE)),not(inSynchronized()),assignment(Matchers.<ExpressionTree>anything(),toType(BinaryTree.class,Matchers.<BinaryTree>allOf(Matchers.<BinaryTree>anyOf(kindIs(Kind.PLUS),kindIs(Kind.MINUS)),binaryTree(sameVariable(variable),Matchers.<ExpressionTree>anything())))));
}
