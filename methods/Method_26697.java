@Override public JCNewClass inline(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.maker().NewClass((getEnclosingExpression() == null) ? null : getEnclosingExpression().inline(inliner),inliner.<JCExpression>inlineList(getTypeArguments()),getIdentifier().inline(inliner),inliner.<JCExpression>inlineList(getArguments()),(getClassBody() == null) ? null : getClassBody().inline(inliner));
}
