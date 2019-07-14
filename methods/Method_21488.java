@Override public RestResponse buildResponse(GetIndexResponse getIndexResponse,XContentBuilder builder) throws Exception {
  GetIndexRequest.Feature[] features=getIndexRequest.features();
  String[] indices=getIndexResponse.indices();
  builder.startObject();
  for (  String index : indices) {
    builder.startObject(index);
    for (    GetIndexRequest.Feature feature : features) {
switch (feature) {
case ALIASES:
        writeAliases(getIndexResponse.aliases().get(index),builder,channel.request());
      break;
case MAPPINGS:
    writeMappings(getIndexResponse.mappings().get(index),builder,channel.request());
  break;
case SETTINGS:
writeSettings(getIndexResponse.settings().get(index),builder,channel.request());
break;
default :
throw new IllegalStateException("feature [" + feature + "] is not valid");
}
}
builder.endObject();
}
builder.endObject();
return new BytesRestResponse(RestStatus.OK,builder);
}
