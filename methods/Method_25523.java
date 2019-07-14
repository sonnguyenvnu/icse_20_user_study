/** 
 * Returns the type that this expression tree will evaluate to. If its a literal, an identifier, or a member select this is the actual type, if its a method invocation then its the return type of the method (after instantiating generic types), if its a constructor then its the type of the returned class. <p>TODO(andrewrice) consider replacing  {@code getReturnType} with this method
 * @param expressionTree the tree to evaluate
 * @return the result type of this tree or null if unable to resolve it
 */
@Nullable public static Type getResultType(ExpressionTree expressionTree){
  Type type=ASTHelpers.getType(expressionTree);
  return type == null ? null : Optional.ofNullable(type.getReturnType()).orElse(type);
}
