@Override public Description matchMethod(MethodTree tree,VisitorState state){
  if (hasAnnotation(NoAllocation.class).matches(tree,state)) {
    return NO_MATCH;
  }
  MethodSymbol symbol=ASTHelpers.getSymbol(tree);
  if (symbol == null) {
    return NO_MATCH;
  }
  return findSuperMethods(symbol,state.getTypes()).stream().filter(s -> ASTHelpers.hasAnnotation(s,NoAllocation.class.getName(),state)).findAny().map(s -> {
    String message=String.format("Method overrides %s in %s which is annotated @NoAllocation," + " it should also be annotated.",s.getSimpleName(),s.owner.getSimpleName());
    return buildDescription(tree).setMessage(message).addFix(SuggestedFix.builder().addImport(NoAllocation.class.getName()).prefixWith(tree,"@NoAllocation ").build()).build();
  }
).orElse(NO_MATCH);
}
