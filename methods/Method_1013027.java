public void fill(){
  SwaggerFields.parametersWithRef().forEach(this::addUnique);
}
