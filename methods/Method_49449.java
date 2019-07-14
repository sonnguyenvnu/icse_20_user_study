@Override public void clusterHealthRequest(String timeout) throws IOException {
  final Map<String,String> params=ImmutableMap.of("wait_for_status","yellow","timeout",timeout);
  final Response response=delegate.performRequest(REQUEST_TYPE_GET,REQUEST_SEPARATOR + "_cluster" + REQUEST_SEPARATOR + "health",params);
  try (final InputStream inputStream=response.getEntity().getContent()){
    final Map<String,Object> values=mapReader.readValue(inputStream);
    if (!values.containsKey("timed_out")) {
      throw new IOException("Unexpected response for Elasticsearch cluster health request");
    }
 else     if (!Objects.equals(values.get("timed_out"),false)) {
      throw new IOException("Elasticsearch timeout waiting for yellow status");
    }
  }
 }
