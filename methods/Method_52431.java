private SimpleTypedNameDeclaration convertToSimpleType(ASTClassOrInterfaceType t){
  String typeImage=t.getImage();
  typeImage=qualifyTypeName(typeImage);
  Node declaringNode=getEnclosingScope(SourceFileScope.class).getQualifiedTypeNames().get(typeImage);
  return new SimpleTypedNameDeclaration(typeImage,this.getEnclosingScope(SourceFileScope.class).resolveType(typeImage),determineSuper(declaringNode));
}
