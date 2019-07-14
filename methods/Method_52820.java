protected Set<NameDeclaration> findVariableHere(PLSQLNameOccurrence occurrence){
  Set<NameDeclaration> result=new HashSet<>();
  Map<VariableNameDeclaration,List<NameOccurrence>> variableDeclarations=getVariableDeclarations();
  Map<MethodNameDeclaration,List<NameOccurrence>> methodDeclarations=getMethodDeclarations();
  if (occurrence.isThisOrSuper() || occurrence.getImage().equals(className)) {
    if (variableDeclarations.isEmpty() && methodDeclarations.isEmpty()) {
      return result;
    }
    if (!variableDeclarations.isEmpty()) {
      result.add(variableDeclarations.keySet().iterator().next());
      return result;
    }
    result.add(methodDeclarations.keySet().iterator().next());
    return result;
  }
  if (occurrence.isMethodOrConstructorInvocation()) {
    for (    MethodNameDeclaration mnd : methodDeclarations.keySet()) {
      if (mnd.getImage().equals(occurrence.getImage())) {
        int args=occurrence.getArgumentCount();
        if (args == mnd.getParameterCount() || mnd.isVarargs() && args >= mnd.getParameterCount() - 1) {
          result.add(mnd);
        }
      }
    }
    return result;
  }
  List<String> images=new ArrayList<>();
  images.add(occurrence.getImage());
  if (null == occurrence.getImage()) {
    if (LOGGER.isLoggable(Level.FINEST)) {
      LOGGER.finest("occurrence==" + occurrence.toString() + "with Argumanet Count == " + occurrence.getArgumentCount() + " for className=" + className);
    }
  }
  if (occurrence.getImage().startsWith(className)) {
    images.add(clipClassName(occurrence.getImage()));
  }
  ImageFinderFunction finder=new ImageFinderFunction(images);
  Applier.apply(finder,getVariableDeclarations().keySet().iterator());
  if (finder.getDecl() != null) {
    result.add(finder.getDecl());
  }
  return result;
}
