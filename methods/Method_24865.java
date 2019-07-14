void handleShowUsage(PreprocessedSketch ps,int tabIndex,int startTabOffset,int stopTabOffset){
  int startJavaOffset=ps.tabOffsetToJavaOffset(tabIndex,startTabOffset);
  int stopJavaOffset=ps.tabOffsetToJavaOffset(tabIndex,stopTabOffset);
  SimpleName name=ASTUtils.getSimpleNameAt(ps.compilationUnit,startJavaOffset,stopJavaOffset);
  if (name == null) {
    editor.statusMessage("Cannot find any name under cursor",EditorStatus.NOTICE);
    return;
  }
  IBinding binding=ASTUtils.resolveBinding(name);
  if (binding == null) {
    editor.statusMessage("Cannot find usages, try to fix errors in your code first",EditorStatus.NOTICE);
    return;
  }
  findUsageAndUpdateTree(ps,binding);
}
