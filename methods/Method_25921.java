@Override public Description matchClass(ClassTree classTree,VisitorState visitorState){
  List<VariableTree> originalClassMembers=classTree.getMembers().stream().filter(mem -> mem instanceof VariableTree).map(mem -> (VariableTree)mem).filter(mem -> !isSuppressed(ASTHelpers.getSymbol(mem)) && !isIgnoredType(mem) && !isStatic(mem)).collect(toCollection(ArrayList::new));
  ClassSymbol classSymbol=ASTHelpers.getSymbol(classTree);
  while (!Objects.equals(classSymbol.getSuperclass(),Type.noType)) {
    TypeSymbol parentSymbol=classSymbol.getSuperclass().asElement();
    List<Symbol> parentElements=parentSymbol.getEnclosedElements();
    Map<Name,VarSymbol> parentMembers=parentElements.stream().filter(mem -> (mem instanceof VarSymbol)).map(mem -> (VarSymbol)mem).filter(mem -> (!mem.isPrivate() && !mem.getModifiers().contains(Modifier.STATIC))).collect(Collectors.toMap(Symbol::getSimpleName,mem -> mem));
    checkForHiddenFields(originalClassMembers,parentMembers,parentSymbol.getSimpleName(),classTree,visitorState);
    classSymbol=(ClassSymbol)parentSymbol;
  }
  return Description.NO_MATCH;
}
