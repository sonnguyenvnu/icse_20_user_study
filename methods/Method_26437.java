/** 
 * A heuristic for whether a method may have been invoked with an expected value. <p>We match cases such as  {@code assertThat(expectedFoo)},  {@code assertThat(expectedBar.id())}, but not if the identifier extends  {@link Throwable} (as this isoften named  {@code expectedException} or similar).
 */
private static boolean expectedValue(ExpressionTree tree,VisitorState state){
  if (!(tree instanceof MethodInvocationTree)) {
    return false;
  }
  MethodInvocationTree methodTree=(MethodInvocationTree)tree;
  IdentifierTree identifier=getRootIdentifier(getOnlyElement(methodTree.getArguments()));
  if (identifier == null) {
    return false;
  }
  Type throwable=Suppliers.typeFromClass(Throwable.class).get(state);
  return Ascii.toLowerCase(identifier.getName().toString()).contains("expected") && !ASTHelpers.isSubtype(ASTHelpers.getType(identifier),throwable,state);
}
