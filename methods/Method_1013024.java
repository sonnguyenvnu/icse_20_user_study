public void fill(){
  SwaggerFields.operation().forEach(this::addUnique);
}
