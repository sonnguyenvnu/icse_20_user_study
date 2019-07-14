protected void populateHeaderAndBody(final Buffer target,final Buffer formBuffer,final boolean fullRequest){
  for (  String name : headers.names()) {
    List<String> values=headers.getAll(name);
    String key=capitalizeHeaderKeys ? HttpUtil.prepareHeaderParameterName(name) : name;
    target.append(key);
    target.append(": ");
    int count=0;
    for (    String value : values) {
      if (count++ > 0) {
        target.append(", ");
      }
      target.append(value);
    }
    target.append(CRLF);
  }
  if (fullRequest) {
    target.append(CRLF);
    if (form != null) {
      target.append(formBuffer);
    }
 else     if (body != null) {
      target.append(body);
    }
  }
}
