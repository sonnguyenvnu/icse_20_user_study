public void fill(){
  OpenApiFields.response().forEach(this::addUnique);
}
