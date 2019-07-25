public void fill(){
  SwaggerFields.parameters().forEach(this::addUnique);
}
