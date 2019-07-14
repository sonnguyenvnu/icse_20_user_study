@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!ARRAYS_AS_LIST_SINGLE_ARRAY.matches(tree,state)) {
    return NO_MATCH;
  }
  ExpressionTree array=Iterables.getOnlyElement(tree.getArguments());
  Type componentType=((ArrayType)ASTHelpers.getType(array)).getComponentType();
  if (!componentType.isPrimitive()) {
    return NO_MATCH;
  }
  String guavaUtils=GUAVA_UTILS.get(componentType.getKind());
  if (guavaUtils == null) {
    return NO_MATCH;
  }
  Fix fix=SuggestedFix.builder().addImport("com.google.common.primitives." + guavaUtils).replace(tree.getMethodSelect(),guavaUtils + ".asList").build();
  return describeMatch(tree,fix);
}
