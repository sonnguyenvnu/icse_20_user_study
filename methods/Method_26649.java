@Override public JCDoWhileLoop inline(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.maker().DoLoop(getStatement().inline(inliner),getCondition().inline(inliner));
}
