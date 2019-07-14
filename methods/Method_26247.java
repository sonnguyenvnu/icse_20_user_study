private static ImmutableSetMultimap<ImportTree,Symbol> getImportedSymbols(CompilationUnitTree compilationUnitTree,VisitorState state){
  ImmutableSetMultimap.Builder<ImportTree,Symbol> builder=ImmutableSetMultimap.builder();
  for (  ImportTree importTree : compilationUnitTree.getImports()) {
    builder.putAll(importTree,getImportedSymbols(importTree,state));
  }
  return builder.build();
}
