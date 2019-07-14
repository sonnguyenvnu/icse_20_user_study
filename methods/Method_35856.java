protected String formatRequest(Request request){
  StringBuilder sb=new StringBuilder();
  sb.append(request.getClientIp()).append(" - ").append(request.getMethod()).append(" ").append(request.getUrl());
  if (request.isBrowserProxyRequest()) {
    sb.append(" (via browser proxy request)");
  }
  sb.append("\n\n");
  sb.append(request.getHeaders());
  if (request.getBody() != null) {
    sb.append(request.getBodyAsString()).append("\n");
  }
  return sb.toString();
}
