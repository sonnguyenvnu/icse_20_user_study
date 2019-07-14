static SuggestedFix replace(DocTree docTree,String replacement,VisitorState state){
  DocSourcePositions positions=JavacTrees.instance(state.context).getSourcePositions();
  CompilationUnitTree compilationUnitTree=state.getPath().getCompilationUnit();
  int startPos=getStartPosition(docTree,state);
  int endPos=(int)positions.getEndPosition(compilationUnitTree,getDocCommentTree(state),docTree);
  return SuggestedFix.replace(startPos,endPos,replacement);
}
