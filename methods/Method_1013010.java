public void fill(){
  OpenApiFields.requestBody().forEach(this::addUnique);
}
