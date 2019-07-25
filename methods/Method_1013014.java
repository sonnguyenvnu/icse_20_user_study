public void fill(){
  OpenApiFields.schema().forEach(this::addUnique);
}
