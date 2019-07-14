@Override public void bulkRequest(List<ElasticSearchMutation> requests,String ingestPipeline) throws IOException {
  final ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
  for (  final ElasticSearchMutation request : requests) {
    final Map actionData=ImmutableMap.of(request.getRequestType().name().toLowerCase(),ImmutableMap.of("_index",request.getIndex(),"_type",request.getType(),"_id",request.getId()));
    outputStream.write(mapWriter.writeValueAsBytes(actionData));
    outputStream.write("\n".getBytes(UTF8_CHARSET));
    if (request.getSource() != null) {
      outputStream.write(mapWriter.writeValueAsBytes(request.getSource()));
      outputStream.write("\n".getBytes(UTF8_CHARSET));
    }
  }
  final StringBuilder builder=new StringBuilder();
  if (ingestPipeline != null) {
    APPEND_OP.apply(builder).append("pipeline=").append(ingestPipeline);
  }
  if (bulkRefresh != null && !bulkRefresh.toLowerCase().equals("false")) {
    APPEND_OP.apply(builder).append("refresh=").append(bulkRefresh);
  }
  builder.insert(0,REQUEST_SEPARATOR + "_bulk");
  final Response response=performRequest(REQUEST_TYPE_POST,builder.toString(),outputStream.toByteArray());
  try (final InputStream inputStream=response.getEntity().getContent()){
    final RestBulkResponse bulkResponse=mapper.readValue(inputStream,RestBulkResponse.class);
    final List<Object> errors=bulkResponse.getItems().stream().flatMap(item -> item.values().stream()).filter(item -> item.getError() != null && item.getStatus() != 404).map(RestBulkItemResponse::getError).collect(Collectors.toList());
    if (!errors.isEmpty()) {
      errors.forEach(error -> log.error("Failed to execute ES query: {}",error));
      throw new IOException("Failure(s) in Elasticsearch bulk request: " + errors);
    }
  }
 }
