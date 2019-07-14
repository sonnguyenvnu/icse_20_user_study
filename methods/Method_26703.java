@Override public JCParens inline(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.maker().Parens(getExpression().inline(inliner));
}
