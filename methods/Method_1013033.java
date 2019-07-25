public void fill(){
  SwaggerFields.securityDefinitions().forEach(this::addUnique);
}
