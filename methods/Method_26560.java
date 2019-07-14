/** 
 * Returns all the ways this tree might be unified with the arguments to this placeholder invocation. That is, if the placeholder invocation looks like  {@code placeholder(arg1, arg2,...)}, then the  {@code Choice} will contain any ways this tree can be unified with {@code arg1},  {@code arg2}, or the other arguments.
 */
Choice<State<PlaceholderParamIdent>> tryBindArguments(final ExpressionTree node,final State<?> state){
  return Choice.from(arguments().entrySet()).thenChoose((  final Map.Entry<UVariableDecl,UExpression> entry) -> unifyParam(entry.getKey(),entry.getValue(),node,state.fork()));
}
