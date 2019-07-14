/** 
 * Add an import for  {@code owner}, and qualify all on demand imported references to members of owner by owner's simple name.
 */
private static void qualifiedNameFix(final SuggestedFix.Builder fix,final Symbol owner,VisitorState state){
  fix.addImport(owner.getQualifiedName().toString());
  final JCCompilationUnit unit=(JCCompilationUnit)state.getPath().getCompilationUnit();
  new TreePathScanner<Void,Void>(){
    @Override public Void visitIdentifier(    IdentifierTree tree,    Void unused){
      Symbol sym=ASTHelpers.getSymbol(tree);
      if (sym == null) {
        return null;
      }
      Tree parent=getCurrentPath().getParentPath().getLeaf();
      if (parent.getKind() == Tree.Kind.CASE && ((CaseTree)parent).getExpression().equals(tree) && sym.owner.getKind() == ElementKind.ENUM) {
        return null;
      }
      if (sym.owner.equals(owner) && unit.starImportScope.includes(sym)) {
        fix.prefixWith(tree,owner.getSimpleName() + ".");
      }
      return null;
    }
  }
.scan(unit,null);
}
