@InternalApi @Deprecated public void setQualifiedName(JavaTypeQualifiedName qualifiedName){
  this.qualifiedName=qualifiedName;
  this.typeDefinition=JavaTypeDefinition.forClass(qualifiedName.getType());
}
