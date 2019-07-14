/** 
 * Finds the parent binder type in the supplied set, if any. 
 */
private @Nullable TypeElement findParentType(TypeElement typeElement,Set<TypeElement> parents){
  TypeMirror type;
  while (true) {
    type=typeElement.getSuperclass();
    if (type.getKind() == TypeKind.NONE) {
      return null;
    }
    typeElement=(TypeElement)((DeclaredType)type).asElement();
    if (parents.contains(typeElement)) {
      return typeElement;
    }
  }
}
