@Override public Description matchMethod(MethodTree tree,VisitorState state){
  MethodSymbol method=ASTHelpers.getSymbol(tree);
  if (method == null) {
    return Description.NO_MATCH;
  }
  ClassSymbol classSym=method.enclClass();
  if (classSym == null) {
    return Description.NO_MATCH;
  }
  TypeSymbol superClass=classSym.getSuperclass().tsym;
  if (superClass == null) {
    return Description.NO_MATCH;
  }
  for (  Symbol s : superClass.members().getSymbols()) {
    if (s.name.contentEquals(method.name) && s.getKind() == ElementKind.METHOD) {
      MethodSymbol supermethod=(MethodSymbol)s;
      if (method.overrides(supermethod,superClass,state.getTypes(),true)) {
        return Description.NO_MATCH;
      }
      if (supermethod.params().size() != method.params().size()) {
        continue;
      }
      for (int x=0; x < method.params().size(); x++) {
        Type methodParamType=method.params().get(x).type;
        Type supermethodParamType=supermethod.params().get(x).type;
        if (methodParamType.tsym.name.contentEquals(supermethodParamType.tsym.name) && !state.getTypes().isSameType(methodParamType,supermethodParamType)) {
          this.supermethod=supermethod;
          return describe(tree,state);
        }
      }
    }
  }
  return Description.NO_MATCH;
}
