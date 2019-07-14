private void doWritHttpResponse(final HttpResponse response,final MutableHttpResponse httpResponse){
  httpResponse.setVersion(response.getVersion());
  httpResponse.setStatus(response.getStatus());
  for (  Map.Entry<String,String[]> entry : response.getHeaders().entrySet()) {
    String key=entry.getKey();
    for (    String value : entry.getValue()) {
      httpResponse.addHeader(key,value);
    }
  }
  httpResponse.setContent(response.getContent());
}
