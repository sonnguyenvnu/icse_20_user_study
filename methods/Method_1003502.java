static String endpoint(String[] indices){
  return new EndpointBuilder().addCommaSeparatedPathParts(indices).build();
}
