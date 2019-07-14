@Override public Response serveFile(String uri,Map<String,String> headers,IHTTPSession session,File file,String mimeType){
  String markdownSource=readSource(file);
  byte[] bytes;
  try {
    bytes=this.processor.markdownToHtml(markdownSource).getBytes("UTF-8");
  }
 catch (  UnsupportedEncodingException e) {
    MarkdownWebServerPlugin.LOG.log(Level.SEVERE,"encoding problem, responding nothing",e);
    bytes=new byte[0];
  }
  return markdownSource == null ? null : Response.newFixedLengthResponse(Status.OK,NanoHTTPD.MIME_HTML,new ByteArrayInputStream(bytes),bytes.length);
}
