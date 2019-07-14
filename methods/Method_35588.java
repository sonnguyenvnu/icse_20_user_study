public ResponseDefinitionBuilder withTransformers(String... responseTransformerNames){
  this.responseTransformerNames=asList(responseTransformerNames);
  return this;
}
