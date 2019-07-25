@Override public void fill(){
  SwaggerFields.schema().forEach(this::addUnique);
}
