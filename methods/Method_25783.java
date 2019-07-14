private static boolean inInstanceMethodOfSubjectImplementation(VisitorState state){
  TreePath pathToEnclosingMethod=state.findPathToEnclosing(MethodTree.class);
  if (pathToEnclosingMethod == null) {
    return false;
  }
  MethodTree enclosingMethod=(MethodTree)pathToEnclosingMethod.getLeaf();
  if (enclosingMethod.getModifiers().getFlags().contains(STATIC)) {
    return false;
  }
  Tree enclosingClass=pathToEnclosingMethod.getParentPath().getLeaf();
  if (enclosingClass == null || enclosingClass.getKind() != CLASS) {
    return false;
  }
  return isSubtype(getDeclaredSymbol(enclosingClass).type,state.getTypeFromString(SUBJECT_CLASS),state);
}
