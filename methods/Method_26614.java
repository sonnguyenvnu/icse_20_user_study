@Override public JCAnnotatedType inline(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.maker().AnnotatedType(List.convert(JCAnnotation.class,inliner.inlineList(getAnnotations())),getUnderlyingType().inline(inliner));
}
