public void fill(){
  OpenApiFields.securityScheme().forEach(this::addUnique);
}
