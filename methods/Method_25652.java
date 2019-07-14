@Override public Description matchClass(ClassTree classTree,VisitorState state){
  if (!fragmentMatcher.matches(classTree,state)) {
    return Description.NO_MATCH;
  }
  String className=ASTHelpers.getSymbol(classTree).toString();
  if (fragmentClasses.contains(className)) {
    return Description.NO_MATCH;
  }
  if (classTree.getModifiers().getFlags().contains(ABSTRACT)) {
    return Description.NO_MATCH;
  }
  if (!classTree.getModifiers().getFlags().contains(PUBLIC)) {
    return buildErrorMessage(classTree,"a fragment must be public");
  }
  if (nestingKind(MEMBER).matches(classTree,state) && !ASTHelpers.getSymbol(classTree).isStatic()) {
    return buildErrorMessage(classTree,"a fragment inner class must be static");
  }
  List<MethodTree> constructors=ASTHelpers.getConstructors(classTree);
  for (  MethodTree constructor : constructors) {
    if (constructor.getParameters().isEmpty()) {
      if (!constructor.getModifiers().getFlags().contains(PUBLIC)) {
        return buildErrorMessage(constructor,"the nullary constructor must be public");
      }
      return Description.NO_MATCH;
    }
  }
  return buildErrorMessage(classTree,"the nullary constructor is missing");
}
