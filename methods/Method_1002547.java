public ExampleRequestResponse finder(String name){
  FinderSchema finderSchema=_resourceSchema.getFinder(name);
  if (finderSchema == null) {
    throw new IllegalArgumentException("No such finder for resource: " + name);
  }
  RecordDataSchema metadataSchema=null;
  if (finderSchema.hasMetadata()) {
    metadataSchema=(RecordDataSchema)RestSpecCodec.textToSchema(finderSchema.getMetadata().getType(),_schemaResolver);
  }
  return buildRequestResponse(buildFinderRequest(finderSchema),buildFinderResult(metadataSchema),buildResourceMethodDescriptorForFinder(name));
}
