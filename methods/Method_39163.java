@Override public void render(final ActionRequest actionRequest,final Object resultValue) throws Exception {
  final HttpServletResponse response=actionRequest.getHttpServletResponse();
  String encoding=response.getCharacterEncoding();
  if (encoding == null) {
    encoding=madvocEncoding.getEncoding();
  }
  response.setContentType(MimeTypes.MIME_APPLICATION_JSON);
  response.setCharacterEncoding(encoding);
  final String json;
  final int status;
  final String statusMessage;
  if (resultValue instanceof JsonResult) {
    JsonResult jsonResult=(JsonResult)resultValue;
    json=jsonResult.value();
    status=jsonResult.status();
    statusMessage=jsonResult.message();
  }
 else {
    json=JsonSerializer.create().deep(true).serialize(resultValue);
    status=200;
    statusMessage="OK";
  }
  response.setStatus(status);
  final byte[] data=json.getBytes(encoding);
  response.setContentLength(data.length);
  OutputStream out=null;
  try {
    out=response.getOutputStream();
    out.write(data);
  }
  finally {
    StreamUtil.close(out);
  }
}
