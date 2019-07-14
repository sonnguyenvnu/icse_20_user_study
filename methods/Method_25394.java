/** 
 * Matches a  {@link MethodInvocation} when the arguments at the two given indices are both thesame variable, as determined by  {@link ASTHelpers#sameVariable}.
 * @param index1 the index of the first actual parameter to test
 * @param index2 the index of the second actual parameter to test
 * @throws IndexOutOfBoundsException if the given indices are invalid
 */
public static Matcher<? super MethodInvocationTree> sameArgument(int index1,int index2){
  return (tree,state) -> {
    List<? extends ExpressionTree> args=tree.getArguments();
    return ASTHelpers.sameVariable(args.get(index1),args.get(index2));
  }
;
}
