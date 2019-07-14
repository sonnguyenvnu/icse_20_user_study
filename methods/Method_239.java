private boolean isInaccessibleViaGeneratedCode(Class<? extends Annotation> annotationClass,String targetThing,Element element){
  boolean hasError=false;
  TypeElement enclosingElement=(TypeElement)element.getEnclosingElement();
  Set<Modifier> modifiers=element.getModifiers();
  if (modifiers.contains(PRIVATE) || modifiers.contains(STATIC)) {
    error(element,"@%s %s must not be private or static. (%s.%s)",annotationClass.getSimpleName(),targetThing,enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  if (enclosingElement.getKind() != CLASS) {
    error(enclosingElement,"@%s %s may only be contained in classes. (%s.%s)",annotationClass.getSimpleName(),targetThing,enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  if (enclosingElement.getModifiers().contains(PRIVATE)) {
    error(enclosingElement,"@%s %s may not be contained in private classes. (%s.%s)",annotationClass.getSimpleName(),targetThing,enclosingElement.getQualifiedName(),element.getSimpleName());
    hasError=true;
  }
  return hasError;
}
