public static Type from(DeclaredType declaredType){
  if (declaredType.getTypeArguments().isEmpty()) {
    return new TypeClass(QualifiedName.of(asElement(declaredType)),ImmutableList.<TypeParameterElement>of());
  }
 else {
    return new TypeImpl(QualifiedName.of(asElement(declaredType)),declaredType.getTypeArguments());
  }
}
