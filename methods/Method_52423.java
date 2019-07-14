@Override protected Set<NameDeclaration> findVariableHere(JavaNameOccurrence occurrence){
  if (occurrence.isThisOrSuper() || className.equals(occurrence.getImage())) {
    return Collections.<NameDeclaration>singleton(classDeclaration);
  }
  Map<MethodNameDeclaration,List<NameOccurrence>> methodDeclarations=getMethodDeclarations();
  Set<NameDeclaration> result=new HashSet<>();
  if (occurrence.isMethodOrConstructorInvocation()) {
    final boolean hasAuxclasspath=getEnclosingScope(SourceFileScope.class).hasAuxclasspath();
    matchMethodDeclaration(occurrence,methodDeclarations.keySet(),hasAuxclasspath,result);
    if (isEnum && "valueOf".equals(occurrence.getImage())) {
      result.add(createBuiltInMethodDeclaration("valueOf","String"));
    }
    if (result.isEmpty()) {
      for (      ClassNameDeclaration innerClass : getClassDeclarations().keySet()) {
        matchMethodDeclaration(occurrence,innerClass.getScope().getDeclarations(MethodNameDeclaration.class).keySet(),hasAuxclasspath,result);
      }
    }
    return result;
  }
  if (occurrence.isMethodReference()) {
    for (    MethodNameDeclaration mnd : methodDeclarations.keySet()) {
      if (mnd.getImage().equals(occurrence.getImage())) {
        result.add(mnd);
      }
    }
    return result;
  }
  List<String> images=new ArrayList<>();
  if (occurrence.getImage() != null) {
    images.add(occurrence.getImage());
    if (occurrence.getImage().startsWith(className)) {
      images.add(clipClassName(occurrence.getImage()));
    }
  }
  Map<VariableNameDeclaration,List<NameOccurrence>> variableDeclarations=getVariableDeclarations();
  ImageFinderFunction finder=new ImageFinderFunction(images);
  Applier.apply(finder,variableDeclarations.keySet().iterator());
  if (finder.getDecl() != null) {
    result.add(finder.getDecl());
  }
  Map<ClassNameDeclaration,List<NameOccurrence>> classDeclarations=getClassDeclarations();
  if (result.isEmpty() && !classDeclarations.isEmpty()) {
    for (    ClassNameDeclaration innerClass : getClassDeclarations().keySet()) {
      Applier.apply(finder,innerClass.getScope().getDeclarations(VariableNameDeclaration.class).keySet().iterator());
      if (finder.getDecl() != null) {
        result.add(finder.getDecl());
      }
    }
  }
  return result;
}
