public void parser(final InputStream file){
  this.parameterMap.putAll(ParameterServiceCollector.collect(file));
}
