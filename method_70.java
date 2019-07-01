@Override public void _XXXXX_(List<PolicyDefinition> policies){
  WebResource r=client.resource(basePath + METADATA_POLICIES_BATCH_PATH);
  r.accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON).post(policies);
}