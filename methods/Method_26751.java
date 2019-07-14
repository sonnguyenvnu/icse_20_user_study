@Override public JCTypeCast inline(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.maker().TypeCast(getType().inline(inliner),getExpression().inline(inliner));
}
