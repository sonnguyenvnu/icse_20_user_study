private boolean checkForAdditionalInfo(Request request){
  if (request == null) {
    return false;
  }
  if (!request.getHeaders().isEmpty() || !request.getCookies().isEmpty()) {
    return true;
  }
  if (StringUtils.isNotBlank(request.getCharset()) || StringUtils.isNotBlank(request.getMethod())) {
    return true;
  }
  if (request.isBinaryContent() || request.getRequestBody() != null) {
    return true;
  }
  if (request.getExtras() != null && !request.getExtras().isEmpty()) {
    return true;
  }
  if (request.getPriority() != 0L) {
    return true;
  }
  return false;
}
