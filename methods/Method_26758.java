@Override public JCTypeUnion inline(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.maker().TypeUnion(inliner.inlineList(getTypeAlternatives()));
}
