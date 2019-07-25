public void fill(){
  OpenApiFields.callback().forEach(this::addUnique);
}
