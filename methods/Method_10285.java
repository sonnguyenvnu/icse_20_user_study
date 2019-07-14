private void makeRequest() throws IOException {
  if (isCancelled()) {
    return;
  }
  if (request.getURI().getScheme() == null) {
    throw new MalformedURLException("No valid URI scheme was provided");
  }
  if (responseHandler instanceof RangeFileAsyncHttpResponseHandler) {
    ((RangeFileAsyncHttpResponseHandler)responseHandler).updateRequestHeaders(request);
  }
  HttpResponse response=client.execute(request,context);
  if (isCancelled()) {
    return;
  }
  responseHandler.onPreProcessResponse(responseHandler,response);
  if (isCancelled()) {
    return;
  }
  responseHandler.sendResponseMessage(response);
  if (isCancelled()) {
    return;
  }
  responseHandler.onPostProcessResponse(responseHandler,response);
}
