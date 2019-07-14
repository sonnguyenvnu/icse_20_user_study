@Override public JCBlock inline(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.maker().Block(0,inlineStatementList(getStatements(),inliner));
}
