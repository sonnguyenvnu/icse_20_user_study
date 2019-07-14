private static ImmutableList<PropDefaultModel> extractFromField(Element enclosedElement){
  if (enclosedElement.getKind() != ElementKind.FIELD) {
    return ImmutableList.of();
  }
  final VariableElement variableElement=(VariableElement)enclosedElement;
  final Annotation propDefaultAnnotation=variableElement.getAnnotation(PropDefault.class);
  if (propDefaultAnnotation == null) {
    return ImmutableList.of();
  }
  final ResType propDefaultResType=((PropDefault)propDefaultAnnotation).resType();
  final int propDefaultResId=((PropDefault)propDefaultAnnotation).resId();
  return ImmutableList.of(new PropDefaultModel(TypeName.get(variableElement.asType()),variableElement.getSimpleName().toString(),ImmutableList.copyOf(new ArrayList<>(variableElement.getModifiers())),variableElement,propDefaultResType,propDefaultResId));
}
