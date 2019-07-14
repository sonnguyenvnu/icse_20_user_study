JCTree inlineBody(Inliner inliner) throws CouldNotResolveImportException {
  if (getBody() instanceof UPlaceholderExpression) {
    UPlaceholderExpression body=(UPlaceholderExpression)getBody();
    Optional<List<JCStatement>> blockBinding=inliner.getOptionalBinding(body.placeholder().blockKey());
    if (blockBinding.isPresent()) {
      List<JCStatement> blockInlined=UPlaceholderExpression.copier(body.arguments(),inliner).copy(blockBinding.get(),inliner);
      if (blockInlined.size() == 1) {
        if (blockInlined.get(0) instanceof JCReturn) {
          return ((JCReturn)blockInlined.get(0)).getExpression();
        }
 else         if (blockInlined.get(0) instanceof JCExpressionStatement) {
          return ((JCExpressionStatement)blockInlined.get(0)).getExpression();
        }
      }
      return inliner.maker().Block(0,blockInlined);
    }
  }
  return getBody().inline(inliner);
}
