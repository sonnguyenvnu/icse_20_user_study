@Override public void fill(){
  SwaggerFields.headers().forEach(this::addUnique);
}
