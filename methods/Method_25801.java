@Override public Description matchAnnotation(AnnotationTree annoTree,VisitorState state){
  if (!IS_COMPATIBLE_WITH_ANNOTATION.matches(annoTree,state)) {
    return Description.NO_MATCH;
  }
  MethodTree methodTree=ASTHelpers.findEnclosingNode(state.getPath(),MethodTree.class);
  MethodSymbol declaredMethod=ASTHelpers.getSymbol(methodTree);
  for (  MethodSymbol methodSymbol : ASTHelpers.findSuperMethods(declaredMethod,state.getTypes())) {
    if (methodSymbol.params().stream().anyMatch(p -> ASTHelpers.hasAnnotation(p,CompatibleWith.class,state))) {
      return describeWithMessage(annoTree,String.format("This method overrides a method in %s that already has @CompatibleWith",methodSymbol.owner.getSimpleName()));
    }
  }
  List<TypeVariableSymbol> potentialTypeVars=new ArrayList<>(declaredMethod.getTypeParameters());
  ClassSymbol cs=(ClassSymbol)declaredMethod.owner;
  do {
    potentialTypeVars.addAll(cs.getTypeParameters());
    cs=cs.isInner() ? cs.owner.enclClass() : null;
  }
 while (cs != null);
  if (potentialTypeVars.isEmpty()) {
    return describeWithMessage(annoTree,"There are no type arguments in scope to match against.");
  }
  Set<String> validNames=potentialTypeVars.stream().map(TypeVariableSymbol::getSimpleName).map(Object::toString).collect(toImmutableSet());
  String constValue=valueArgumentFromCompatibleWithAnnotation(annoTree);
  if (isNullOrEmpty(constValue)) {
    return describeWithMessage(annoTree,String.format("The value of @CompatibleWith must not be empty (valid arguments are %s)",printTypeArgs(validNames)));
  }
  return validNames.contains(constValue) ? Description.NO_MATCH : describeWithMessage(annoTree,String.format("%s is not a valid type argument. Valid arguments are: %s",constValue,printTypeArgs(validNames)));
}
