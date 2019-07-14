@Override public Description matchCompilationUnit(CompilationUnitTree tree,VisitorState state){
  ProtoNullComparisonScanner scanner=new ProtoNullComparisonScanner(state);
  scanner.scan(state.getPath(),null);
  return Description.NO_MATCH;
}
