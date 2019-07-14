private void validateElement(final Element element){
  TypeMirror requiredAnnotationTypeMirror=processingEnv.getElementUtils().getTypeElement(RequiredAnnotation.class.getName()).asType();
  for (  final AnnotationMirror annotation : processingEnv.getElementUtils().getAllAnnotationMirrors(element)) {
    AnnotationMirror requiredAnnotationMirror=getAnnotationMirror(annotation.getAnnotationType().asElement(),requiredAnnotationTypeMirror);
    if (requiredAnnotationMirror == null) {
      continue;
    }
    AnnotationValue value=getAnnotationValue(requiredAnnotationMirror,"value");
    if (value == null) {
      continue;
    }
    new SimpleAnnotationValueVisitor7<Void,Void>(){
      @Override public Void visitType(      TypeMirror t,      Void p){
        if (getAnnotationMirror(element,t) == null) {
          printError(element,annotation,"Annotation %s on %s also requires %s",annotation,element,t);
        }
        return null;
      }
      @Override public Void visitArray(      List<? extends AnnotationValue> vals,      Void p){
        for (        AnnotationValue val : vals) {
          visit(val);
        }
        return null;
      }
    }
.visit(value);
  }
  validateElements(element.getEnclosedElements());
}
