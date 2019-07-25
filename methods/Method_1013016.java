public void fill(){
  OpenApiFields.server().forEach(this::addUnique);
}
