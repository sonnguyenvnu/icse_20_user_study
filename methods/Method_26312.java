/** 
 * Creates a  {@link GuardedByExpression} from a bound AST node, or returns {@code Optional.empty()} if the AST node doesn't correspond to a 'simple' lock expression.
 */
public static Optional<GuardedByExpression> bindExpression(JCTree.JCExpression exp,VisitorState visitorState){
  try {
    return Optional.of(bind(exp,BinderContext.of(ALREADY_BOUND_RESOLVER,ASTHelpers.getSymbol(visitorState.findEnclosing(ClassTree.class)),visitorState.getTypes(),visitorState.getNames())));
  }
 catch (  IllegalGuardedBy expected) {
    return Optional.empty();
  }
}
