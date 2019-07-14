private static boolean isTypeAnnotation(AnnotationTree t){
  Symbol annotationSymbol=ASTHelpers.getSymbol(t.getAnnotationType());
  if (annotationSymbol == null) {
    return false;
  }
  Target target=annotationSymbol.getAnnotation(Target.class);
  if (target == null) {
    return false;
  }
  List<ElementType> value=Arrays.asList(target.value());
  return value.contains(ElementType.TYPE_USE) || value.contains(ElementType.TYPE_PARAMETER);
}
