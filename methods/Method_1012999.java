public void fill(){
  OpenApiFields.encoding().forEach(this::addUnique);
}
