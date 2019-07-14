@Override public JCNewArray inline(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.maker().NewArray((getType() == null) ? null : getType().inline(inliner),(getDimensions() == null) ? null : inliner.<JCExpression>inlineList(getDimensions()),(getInitializers() == null) ? null : inliner.<JCExpression>inlineList(getInitializers()));
}
