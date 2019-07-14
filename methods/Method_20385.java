/** 
 * Get information about methods returning class type of the original class so we can duplicate them in the generated class for chaining purposes
 */
protected void collectMethodsReturningClassType(TypeElement modelClass,Types typeUtils){
  TypeElement clazz=modelClass;
  while (clazz.getSuperclass().getKind() != TypeKind.NONE) {
    for (    Element subElement : clazz.getEnclosedElements()) {
      Set<Modifier> modifiers=subElement.getModifiers();
      if (subElement.getKind() == ElementKind.METHOD && !modifiers.contains(Modifier.PRIVATE) && !modifiers.contains(Modifier.FINAL) && !modifiers.contains(Modifier.STATIC)) {
        TypeMirror methodReturnType=((ExecutableType)subElement.asType()).getReturnType();
        if (methodReturnType.equals(clazz.asType()) || typeUtils.isSubtype(clazz.asType(),methodReturnType)) {
          ExecutableElement castedSubElement=((ExecutableElement)subElement);
          List<? extends VariableElement> params=castedSubElement.getParameters();
          String methodName=subElement.getSimpleName().toString();
          if (methodName.equals(RESET_METHOD) && params.isEmpty()) {
            continue;
          }
          methodsReturningClassType.add(new MethodInfo(methodName,modifiers,buildParamSpecs(params),castedSubElement.isVarArgs()));
        }
      }
    }
    clazz=(TypeElement)typeUtils.asElement(clazz.getSuperclass());
  }
}
