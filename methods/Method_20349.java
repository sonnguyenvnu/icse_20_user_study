/** 
 * Check if the given class or any of its super classes have a super method with the given name. Private methods are ignored since the generated subclass can't call super on those.
 */
protected boolean hasSuperMethod(TypeElement classElement,Element attribute){
  if (!Utils.isEpoxyModel(classElement.asType())) {
    return false;
  }
  for (  Element subElement : classElement.getEnclosedElements()) {
    if (subElement.getKind() == ElementKind.METHOD) {
      ExecutableElement method=(ExecutableElement)subElement;
      if (!method.getModifiers().contains(Modifier.PRIVATE) && method.getSimpleName().toString().equals(attribute.getSimpleName().toString()) && method.getParameters().size() == 1 && method.getParameters().get(0).asType().equals(attribute.asType())) {
        return true;
      }
    }
  }
  Element superClass=typeUtils.asElement(classElement.getSuperclass());
  return (superClass instanceof TypeElement) && hasSuperMethod((TypeElement)superClass,attribute);
}
