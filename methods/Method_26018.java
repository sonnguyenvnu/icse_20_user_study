static int getEndPosition(DocTree docTree,VisitorState state){
  DocSourcePositions positions=JavacTrees.instance(state.context).getSourcePositions();
  CompilationUnitTree compilationUnitTree=state.getPath().getCompilationUnit();
  return (int)positions.getEndPosition(compilationUnitTree,getDocCommentTree(state),docTree);
}
