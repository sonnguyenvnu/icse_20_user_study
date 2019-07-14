private static boolean suppresses(final Node node,Rule rule){
  return node instanceof CanSuppressWarnings && ((CanSuppressWarnings)node).hasSuppressWarningsAnnotationFor(rule);
}
