static TypeSpec extractDiffTypeIfNecessary(TypeSpec typeSpec){
  if (typeSpec.isSameDeclaredType(DIFF)) {
    return ((TypeSpec.DeclaredTypeSpec)typeSpec).getTypeArguments().get(0);
  }
  return typeSpec;
}
