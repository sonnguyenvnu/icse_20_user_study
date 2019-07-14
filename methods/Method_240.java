private boolean isBindingInWrongPackage(Class<? extends Annotation> annotationClass,Element element){
  TypeElement enclosingElement=(TypeElement)element.getEnclosingElement();
  String qualifiedName=enclosingElement.getQualifiedName().toString();
  if (qualifiedName.startsWith("android.")) {
    error(element,"@%s-annotated class incorrectly in Android framework package. (%s)",annotationClass.getSimpleName(),qualifiedName);
    return true;
  }
  if (qualifiedName.startsWith("java.")) {
    error(element,"@%s-annotated class incorrectly in Java framework package. (%s)",annotationClass.getSimpleName(),qualifiedName);
    return true;
  }
  return false;
}
