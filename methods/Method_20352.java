/** 
 * Keeps track of annotations on the attribute so that they can be used in the generated setter and getter method. Setter and getter annotations are stored separately since the annotation may not target both method and parameter types.
 */
private void buildAnnotationLists(List<? extends AnnotationMirror> annotationMirrors){
  for (  AnnotationMirror annotationMirror : annotationMirrors) {
    if (!annotationMirror.getElementValues().isEmpty()) {
      continue;
    }
    ClassName annotationClass=ClassName.bestGuess(annotationMirror.getAnnotationType().toString());
    if (annotationClass.equals(ClassName.get(EpoxyAttribute.class))) {
      continue;
    }
    DeclaredType annotationType=annotationMirror.getAnnotationType();
    Target targetAnnotation=annotationType.asElement().getAnnotation(Target.class);
    List<ElementType> elementTypes=Arrays.asList(targetAnnotation == null ? ElementType.values() : targetAnnotation.value());
    AnnotationSpec annotationSpec=AnnotationSpec.builder(annotationClass).build();
    if (elementTypes.contains(ElementType.PARAMETER)) {
      getSetterAnnotations().add(annotationSpec);
    }
    if (elementTypes.contains(ElementType.METHOD)) {
      getGetterAnnotations().add(annotationSpec);
    }
  }
}
