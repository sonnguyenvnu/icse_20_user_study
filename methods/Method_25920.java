private Fix replaceMethodName(MethodInvocationTree tree,VisitorState state,String newName){
  String source=state.getSourceForNode((JCTree)tree.getMethodSelect());
  int idx=source.lastIndexOf("contains");
  String replacement=source.substring(0,idx) + newName + source.substring(idx + "contains".length());
  Fix fix=SuggestedFix.replace(tree.getMethodSelect(),replacement);
  return fix;
}
