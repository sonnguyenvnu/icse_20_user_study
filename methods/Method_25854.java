@Override public Description matchMethod(MethodTree tree,VisitorState state){
  MethodSymbol symbol=ASTHelpers.getSymbol(tree);
  if (symbol == null) {
    return NO_MATCH;
  }
  if (hasAnnotation(tree,DO_NOT_CALL,state)) {
    if (symbol.getModifiers().contains(Modifier.PRIVATE)) {
      return buildDescription(tree).setMessage("A private method that should not be called should simply be removed.").build();
    }
    if (symbol.getModifiers().contains(Modifier.ABSTRACT)) {
      return NO_MATCH;
    }
    if (symbol.getModifiers().contains(Modifier.FINAL)) {
      return NO_MATCH;
    }
    if (symbol.owner.enclClass().getModifiers().contains(Modifier.FINAL)) {
      return NO_MATCH;
    }
    return buildDescription(tree).setMessage("Methods annotated with @DoNotCall should be final.").addFix(SuggestedFixes.addModifiers(tree,state,Modifier.FINAL)).build();
  }
  return findSuperMethods(symbol,state.getTypes()).stream().filter(s -> hasAnnotation(s,DO_NOT_CALL,state)).findAny().map(s -> {
    String message=String.format("Method overrides %s in %s which is annotated @DoNotCall," + " it should also be annotated.",s.getSimpleName(),s.owner.getSimpleName());
    return buildDescription(tree).setMessage(message).addFix(SuggestedFix.builder().addImport(DO_NOT_CALL).prefixWith(tree,"@DoNotCall ").build()).build();
  }
).orElse(NO_MATCH);
}
