private static Optional<SuggestedFix> fixConstructor(NewClassTree constructor,VarSymbol exception,VisitorState state){
  Symbol symbol=ASTHelpers.getSymbol(((JCNewClass)constructor).clazz);
  if (!(symbol instanceof ClassSymbol)) {
    return Optional.empty();
  }
  ClassSymbol classSymbol=(ClassSymbol)symbol;
  ImmutableList<MethodSymbol> constructors=classSymbol.getEnclosedElements().stream().filter(Symbol::isConstructor).map(e -> (MethodSymbol)e).collect(toImmutableList());
  MethodSymbol constructorSymbol=ASTHelpers.getSymbol(constructor);
  if (constructorSymbol == null) {
    return Optional.empty();
  }
  List<Type> types=getParameterTypes(constructorSymbol);
  for (  MethodSymbol proposedConstructor : constructors) {
    List<Type> proposedTypes=getParameterTypes(proposedConstructor);
    if (proposedTypes.size() != types.size() + 1) {
      continue;
    }
    Set<Modifier> constructorVisibility=Sets.intersection(constructorSymbol.getModifiers(),VISIBILITY_MODIFIERS);
    Set<Modifier> replacementVisibility=Sets.intersection(proposedConstructor.getModifiers(),VISIBILITY_MODIFIERS);
    if (constructor.getClassBody() == null && !constructorVisibility.equals(replacementVisibility)) {
      continue;
    }
    if (typesEqual(proposedTypes.subList(0,types.size()),types,state) && state.getTypes().isAssignable(exception.type,getLast(proposedTypes))) {
      return Optional.of(appendArgument(constructor,exception.getSimpleName().toString(),state,types));
    }
  }
  return Optional.empty();
}
