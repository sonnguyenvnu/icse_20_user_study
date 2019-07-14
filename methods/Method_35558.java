@Override public <P>BasicMappingBuilder withPostServeAction(String extensionName,P parameters){
  Parameters params=parameters instanceof Parameters ? (Parameters)parameters : Parameters.of(parameters);
  postServeActions.put(extensionName,params);
  return this;
}
