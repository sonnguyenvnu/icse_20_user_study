@Override public Description matchMethod(MethodTree tree,VisitorState state){
  List<? extends ExpressionTree> thrown=tree.getThrows();
  if (thrown.isEmpty()) {
    return NO_MATCH;
  }
  SetMultimap<Symbol,ExpressionTree> exceptionsBySuper=LinkedHashMultimap.create();
  for (  ExpressionTree exception : thrown) {
    Type type=getType(exception);
    do {
      type=state.getTypes().supertype(type);
      exceptionsBySuper.put(type.tsym,exception);
    }
 while (!state.getTypes().isSameType(type,state.getSymtab().objectType));
  }
  Set<ExpressionTree> toRemove=new HashSet<>();
  List<String> messages=new ArrayList<>();
  for (  ExpressionTree exception : thrown) {
    Symbol sym=getSymbol(exception);
    if (exceptionsBySuper.containsKey(sym)) {
      Set<ExpressionTree> sub=exceptionsBySuper.get(sym);
      messages.add(String.format("%s %s of %s",oxfordJoin(", ",sub),sub.size() == 1 ? "is a subtype" : "are subtypes",sym.getSimpleName()));
      toRemove.addAll(sub);
    }
  }
  if (toRemove.isEmpty()) {
    return NO_MATCH;
  }
  List<ExpressionTree> delete=ImmutableList.<ExpressionTree>copyOf(Iterables.filter(tree.getThrows(),Predicates.in(toRemove)));
  return buildDescription(delete.get(0)).setMessage("Redundant throws clause: " + oxfordJoin("; ",messages)).addFix(SuggestedFixes.deleteExceptions(tree,state,delete)).build();
}
