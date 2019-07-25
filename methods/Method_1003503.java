static String endpoint(String[] indices,String endpoint,String type){
  return new EndpointBuilder().addCommaSeparatedPathParts(indices).addPathPartAsIs(endpoint).addPathPart(type).build();
}
