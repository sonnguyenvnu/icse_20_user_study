static void validateFieldAccessibleViaGeneratedCode(Element fieldElement,Class<?> annotationClass,ErrorLogger errorLogger,boolean skipPrivateFieldCheck){
  TypeElement enclosingElement=(TypeElement)fieldElement.getEnclosingElement();
  Set<Modifier> modifiers=fieldElement.getModifiers();
  if ((modifiers.contains(PRIVATE) && !skipPrivateFieldCheck) || modifiers.contains(STATIC)) {
    errorLogger.logError("%s annotations must not be on private or static fields. (class: %s, field: %s)",annotationClass.getSimpleName(),enclosingElement.getSimpleName(),fieldElement.getSimpleName());
  }
  if (enclosingElement.getNestingKind().isNested()) {
    if (!enclosingElement.getModifiers().contains(STATIC)) {
      errorLogger.logError("Nested classes with %s annotations must be static. (class: %s, field: %s)",annotationClass.getSimpleName(),enclosingElement.getSimpleName(),fieldElement.getSimpleName());
    }
  }
  if (enclosingElement.getKind() != CLASS) {
    errorLogger.logError("%s annotations may only be contained in classes. (class: %s, field: %s)",annotationClass.getSimpleName(),enclosingElement.getSimpleName(),fieldElement.getSimpleName());
  }
  if (enclosingElement.getModifiers().contains(PRIVATE)) {
    errorLogger.logError("%s annotations may not be contained in private classes. (class: %s, field: %s)",annotationClass.getSimpleName(),enclosingElement.getSimpleName(),fieldElement.getSimpleName());
  }
}
