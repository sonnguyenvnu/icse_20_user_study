@Override public JCCatch inline(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.maker().Catch(getParameter().inline(inliner),getBlock().inline(inliner));
}
