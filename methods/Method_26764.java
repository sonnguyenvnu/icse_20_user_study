@Override public JCWildcard inline(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.maker().Wildcard(inliner.maker().TypeBoundKind(BOUND_KINDS.get(getKind())),(getBound() == null) ? null : getBound().inline(inliner));
}
