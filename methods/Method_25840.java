boolean shouldUseGuava(VisitorState state){
  for (  ImportTree importTree : state.getPath().getCompilationUnit().getImports()) {
    Symbol sym=ASTHelpers.getSymbol(importTree.getQualifiedIdentifier());
    if (sym == null) {
      continue;
    }
    if (sym.getQualifiedName().contentEquals("com.google.common.io.Files")) {
      return true;
    }
  }
  return false;
}
