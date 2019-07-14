private static ImmutableSet<Symbol> getImportedSymbols(ImportTree importTree,VisitorState state){
  if (importTree.isStatic()) {
    StaticImportInfo staticImportInfo=StaticImports.tryCreate(importTree,state);
    return staticImportInfo == null ? ImmutableSet.<Symbol>of() : staticImportInfo.members();
  }
 else {
    @Nullable Symbol importedSymbol=getSymbol(importTree.getQualifiedIdentifier());
    return importedSymbol == null ? ImmutableSet.<Symbol>of() : ImmutableSet.of(importedSymbol);
  }
}
