/** 
 * Check whether  {@code tree} is an AutoValue accessor. A tree is an AutoValue accessor iff:<ul> <li>it is a method invocation <li>of an abstract method <li>with 0 arguments <li>defined on a class annotated @AutoValue </ul> <p>Public visibility for use in NullnessPropagationTransfer#returnValueNullness
 */
public static boolean isAutoValueAccessor(Tree tree){
  if (!(tree instanceof MethodInvocationTree)) {
    return false;
  }
  JCMethodInvocation invocationTree=(JCMethodInvocation)tree;
  JCExpression methodSelect=invocationTree.getMethodSelect();
  Type rcvrType=(methodSelect instanceof JCFieldAccess) ? ((JCFieldAccess)methodSelect).selected.type : ((JCIdent)methodSelect).sym.owner.type;
  return invocationTree.getArguments().isEmpty() && TreeUtils.elementFromUse(invocationTree).getModifiers().contains(Modifier.ABSTRACT) && rcvrType.tsym.getKind() == ElementKind.CLASS && MoreAnnotations.getDeclarationAndTypeAttributes(rcvrType.tsym).map(Object::toString).anyMatch("@com.google.auto.value.AutoValue"::equals);
}
