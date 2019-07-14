private static List<? extends AnnotationTree> findAnnotationsTree(Tree tree){
  ModifiersTree maybeModifiers=getModifiers(tree);
  return maybeModifiers == null ? ImmutableList.of() : maybeModifiers.getAnnotations();
}
