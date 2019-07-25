public void fill(){
  OpenApiFields.path().forEach(this::addUnique);
}
