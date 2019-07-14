private void fixThrows(VisitorState state,SuggestedFix.Builder fix){
  MethodTree methodTree=state.findEnclosing(MethodTree.class);
  if (methodTree == null || methodTree.getThrows().isEmpty()) {
    return;
  }
  ImmutableMap.Builder<Type,ExpressionTree> thrown=ImmutableMap.builder();
  for (  ExpressionTree e : methodTree.getThrows()) {
    thrown.put(ASTHelpers.getType(e),e);
  }
  UnhandledResult<ExpressionTree> result=unhandled(thrown.build(),state);
  if (result.unhandled.isEmpty()) {
    return;
  }
  List<String> newThrows=new ArrayList<>();
  for (  Type handle : result.unhandled) {
    newThrows.add(handle.tsym.getSimpleName().toString());
  }
  Collections.sort(newThrows);
  fix.postfixWith(Iterables.getLast(methodTree.getThrows()),", " + Joiner.on(", ").join(newThrows));
  fix.addImport("java.lang.reflect.InvocationTargetException");
}
