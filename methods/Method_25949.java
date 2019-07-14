private Fix addPrivateConstructor(ClassTree classTree,VisitorState state){
  return SuggestedFixes.addMembers(classTree,state,"private " + classTree.getSimpleName() + "() {}");
}
