@Override public String dump(final Response response){
  HttpResponse httpResponse=(HttpResponse)response;
  return new StringBuilder().append(responseProtocolLine(httpResponse)).append(StringUtil.NEWLINE).append(asHeaders(httpResponse)).append(asContent(httpResponse)).toString();
}
