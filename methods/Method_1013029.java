public void fill(){
  SwaggerFields.responseDefinition().forEach(this::addUnique);
}
