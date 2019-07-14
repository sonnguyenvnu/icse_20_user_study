@Override public JCInstanceOf inline(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.maker().TypeTest(getExpression().inline(inliner),getType().inline(inliner));
}
