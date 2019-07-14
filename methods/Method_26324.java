public static GuardedBySymbolResolver from(ClassSymbol owner,CompilationUnitTree compilationUnit,Context context,Tree leaf,VisitorState visitorState){
  return new GuardedBySymbolResolver(owner,compilationUnit,context,leaf,visitorState);
}
