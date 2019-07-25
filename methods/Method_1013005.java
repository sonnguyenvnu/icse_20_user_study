public void fill(){
  OpenApiFields.mediaType().forEach(this::addUnique);
}
