@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!CONTAINS_MATCHER.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  Description.Builder result=buildDescription(tree);
  List<Type> tyargs=ASTHelpers.getReceiverType(tree).getTypeArguments();
  if (tyargs.size() == 2) {
    Types types=state.getTypes();
    Type key=ASTHelpers.getUpperBound(tyargs.get(0),types);
    Type value=ASTHelpers.getUpperBound(tyargs.get(1),types);
    Type arg=ASTHelpers.getType(Iterables.getOnlyElement(tree.getArguments()));
    boolean valueShaped=types.isAssignable(arg,value);
    boolean keyShaped=types.isAssignable(arg,key);
    if (keyShaped && !valueShaped) {
      result.addFix(replaceMethodName(tree,state,"containsKey"));
      result.setMessage(String.format("contains() is a legacy method that is equivalent to containsValue(), but the " + "argument type '%s' looks like a key",key));
    }
 else     if (valueShaped && !keyShaped) {
      result.addFix(replaceMethodName(tree,state,"containsValue"));
    }
 else     if (valueShaped && keyShaped) {
      result.addFix(replaceMethodName(tree,state,"containsValue"));
      result.addFix(replaceMethodName(tree,state,"containsKey"));
      result.setMessage(String.format("contains() is a legacy method that is equivalent to containsValue(), but the " + "argument type '%s' could be a key or a value",key));
    }
 else {
      throw new AssertionError(String.format("unexpected argument to contains(): key: %s, value: %s, argument: %s",key,value,arg));
    }
  }
 else {
    result.addFix(replaceMethodName(tree,state,"containsValue"));
  }
  return result.build();
}
