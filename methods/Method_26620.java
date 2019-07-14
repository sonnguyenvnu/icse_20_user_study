@Override public JCArrayTypeTree inline(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.maker().TypeArray(getType().inline(inliner));
}
