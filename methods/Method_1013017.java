public void fill(){
  OpenApiFields.serverVariable().forEach(this::addUnique);
}
