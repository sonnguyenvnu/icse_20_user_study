static TreeCopier<Inliner> copier(final Map<UVariableDecl,UExpression> arguments,Inliner inliner){
  return new TreeCopier<Inliner>(inliner.maker()){
    @Override public <T extends JCTree>T copy(    T tree,    Inliner inliner){
      if (tree == null) {
        return null;
      }
      T result=super.copy(tree,inliner);
      if (result.toString().equals(tree.toString())) {
        return tree;
      }
 else {
        return result;
      }
    }
    @Override public JCTree visitIdentifier(    IdentifierTree node,    Inliner inliner){
      if (node instanceof PlaceholderParamIdent) {
        try {
          return arguments.get(((PlaceholderParamIdent)node).param).inline(inliner);
        }
 catch (        CouldNotResolveImportException e) {
          throw new UncheckedCouldNotResolveImportException(e);
        }
      }
 else {
        return super.visitIdentifier(node,inliner);
      }
    }
  }
;
}
