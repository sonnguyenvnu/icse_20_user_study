@Override public void fill(){
  SwaggerValues.mimeTypes().forEach(this::addValue);
}
