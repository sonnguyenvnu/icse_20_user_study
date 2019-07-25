public void fill(){
  OpenApiFields.root().forEach(this::addUnique);
}
