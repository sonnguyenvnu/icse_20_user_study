@Override public void _XXXXX_(List<Kafka2TupleMetadata> k2ts){
  WebResource r=client.resource(basePath + METADATA_DATASOURCES_BATCH_PATH);
  r.accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON).post(k2ts);
}