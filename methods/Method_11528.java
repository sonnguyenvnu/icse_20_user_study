protected Page handleResponse(Request request,String charset,HttpResponse httpResponse,Task task) throws IOException {
  byte[] bytes=IOUtils.toByteArray(httpResponse.getEntity().getContent());
  String contentType=httpResponse.getEntity().getContentType() == null ? "" : httpResponse.getEntity().getContentType().getValue();
  Page page=new Page();
  page.setBytes(bytes);
  if (!request.isBinaryContent()) {
    if (charset == null) {
      charset=getHtmlCharset(contentType,bytes);
    }
    page.setCharset(charset);
    page.setRawText(new String(bytes,charset));
  }
  page.setUrl(new PlainText(request.getUrl()));
  page.setRequest(request);
  page.setStatusCode(httpResponse.getStatusLine().getStatusCode());
  page.setDownloadSuccess(true);
  if (responseHeader) {
    page.setHeaders(HttpClientUtils.convertHeaders(httpResponse.getAllHeaders()));
  }
  return page;
}
