@Override public JCTypeIntersection inline(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.maker().TypeIntersection(inliner.inlineList(getBounds()));
}
