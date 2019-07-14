private void validateImplementsHashCode(TypeMirror mirror) throws EpoxyProcessorException {
  if (mirror.getKind() == TypeKind.ERROR) {
    return;
  }
  if (TypeName.get(mirror).isPrimitive()) {
    return;
  }
  if (mirror.getKind() == TypeKind.ARRAY) {
    validateArrayType((ArrayType)mirror);
    return;
  }
  if (!(mirror instanceof DeclaredType)) {
    return;
  }
  DeclaredType declaredType=(DeclaredType)mirror;
  Element element=typeUtils.asElement(mirror);
  TypeElement clazz=(TypeElement)element;
  if (isIterableType(clazz)) {
    validateIterableType(declaredType);
    return;
  }
  if (isAutoValueType(element)) {
    return;
  }
  if (isWhiteListedType(element)) {
    return;
  }
  if (!hasHashCodeInClassHierarchy(clazz)) {
    throwError("Attribute does not implement hashCode");
  }
  if (!hasEqualsInClassHierarchy(clazz)) {
    throwError("Attribute does not implement equals");
  }
}
