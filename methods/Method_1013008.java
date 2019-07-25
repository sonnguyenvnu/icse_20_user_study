public void fill(){
  OpenApiFields.parameter().forEach(this::addUnique);
}
