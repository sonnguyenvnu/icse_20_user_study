@Path("/streams/create") @POST public OpResult _XXXXX_(StreamDefinitionWrapper stream){
  Preconditions.checkNotNull(stream.getStreamDefinition(),"Stream definition is null");
  Preconditions.checkNotNull(stream.getStreamSource(),"Stream source is null");
  stream.validateAndEnsureDefault();
  OpResult createStreamResult=dao.createStream(stream.getStreamDefinition());
  OpResult createDataSourceResult=dao.addDataSource(stream.getStreamSource());
  if (createStreamResult.code == OpResult.SUCCESS && createDataSourceResult.code == OpResult.SUCCESS) {
    return OpResult.success("Successfully create stream " + stream.getStreamDefinition().getStreamId() + ", and datasource "+ stream.getStreamSource().getName());
  }
 else {
    return OpResult.fail("Error: " + StringUtils.join(new String[]{createDataSourceResult.message,createDataSourceResult.message},","));
  }
}