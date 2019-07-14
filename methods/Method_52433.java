private Class<?> resolveGenericType(String typeImage,List<ASTTypeParameter> types){
  for (  ASTTypeParameter type : types) {
    if (typeImage.equals(type.getImage())) {
      ASTClassOrInterfaceType bound=type.getFirstDescendantOfType(ASTClassOrInterfaceType.class);
      if (bound != null) {
        if (bound.getType() != null) {
          return bound.getType();
        }
 else {
          return this.getEnclosingScope(SourceFileScope.class).resolveType(bound.getImage());
        }
      }
 else {
        return Object.class;
      }
    }
  }
  return null;
}
