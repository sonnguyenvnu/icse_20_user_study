@Override public String dump(final Request request){
  HttpRequest httpRequest=(HttpRequest)request;
  StringBuilder buf=new StringBuilder();
  buf.append(requestProtocolLine(httpRequest)).append(StringUtil.NEWLINE).append(asHeaders(httpRequest)).append(asContent(httpRequest));
  return buf.toString();
}
