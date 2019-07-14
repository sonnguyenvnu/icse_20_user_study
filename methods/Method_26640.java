@Override public JCExpression inline(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.importPolicy().classReference(inliner,getTopLevelClass(),getName());
}
