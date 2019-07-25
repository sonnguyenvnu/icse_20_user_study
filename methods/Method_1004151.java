public static BindingKey get(TypeMirror type,@Nullable AnnotationMirror qualifier){
  Preconditions.checkNotNull(type);
  TypeKind typeKind=type.getKind();
  if (typeKind.equals(TypeKind.ERROR)) {
    throw new ResolveTypeMirrorException(type);
  }
  Preconditions.checkArgument(typeKind.equals(TypeKind.DECLARED) || typeKind.isPrimitive() || typeKind.equals(TypeKind.TYPEVAR) || typeKind.equals(TypeKind.ARRAY),String.format("Unexpected type %s of Kind %s",type,typeKind));
  return new BindingKey(type,qualifier);
}
