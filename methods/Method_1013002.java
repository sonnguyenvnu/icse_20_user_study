public void fill(){
  OpenApiFields.header().forEach(this::addUnique);
}
