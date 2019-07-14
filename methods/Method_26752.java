@Override public JCTypeParameter inline(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.maker().TypeParameter(getName().inline(inliner),inliner.inlineList(getBounds()));
}
