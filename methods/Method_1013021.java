@Override public void fill(){
  SwaggerFields.header().forEach(this::addUnique);
}
