/** 
 * Checks if the given private field has getter and setter for access to it
 */
private void findGetterAndSetterForPrivateField(ErrorLogger errorLogger){
  for (  Element element : classElement.getEnclosedElements()) {
    if (element.getKind() == ElementKind.METHOD) {
      ExecutableElement method=(ExecutableElement)element;
      String methodName=method.getSimpleName().toString();
      if ((methodName.equals(String.format("get%s",capitalizeFirstLetter(getFieldName()))) || methodName.equals(String.format("is%s",capitalizeFirstLetter(getFieldName()))) || (methodName.equals(getFieldName()) && startsWithIs(getFieldName()))) && !method.getModifiers().contains(PRIVATE) && !method.getModifiers().contains(STATIC) && method.getParameters().isEmpty()) {
        setGetterMethodName(methodName);
      }
      if ((methodName.equals(String.format("set%s",capitalizeFirstLetter(getFieldName()))) || (startsWithIs(getFieldName()) && methodName.equals(String.format("set%s",getFieldName().substring(2,getFieldName().length()))))) && !method.getModifiers().contains(PRIVATE) && !method.getModifiers().contains(STATIC) && method.getParameters().size() == 1) {
        setSetterMethodName(methodName);
      }
    }
  }
  if (getGetterMethodName() == null || getSetterMethodName() == null) {
    setPrivate(false);
    errorLogger.logError("%s annotations must not be on private fields" + " without proper getter and setter methods. (class: %s, field: %s)",EpoxyAttribute.class.getSimpleName(),classElement.getSimpleName(),getFieldName());
  }
}
