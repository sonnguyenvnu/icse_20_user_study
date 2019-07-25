public void fill(){
  OpenApiFields.operation().forEach(this::addUnique);
}
