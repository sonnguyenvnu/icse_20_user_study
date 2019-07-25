public void fill(){
  OpenApiFields.discriminator().forEach(this::addUnique);
}
