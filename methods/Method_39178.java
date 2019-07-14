@Override public void render(final ActionRequest actionRequest,final Object resultValue) throws Exception {
  final TextResult textResult;
  if (resultValue == null) {
    textResult=TextResult.of(StringPool.EMPTY);
  }
 else {
    if (resultValue instanceof String) {
      textResult=TextResult.of((String)resultValue);
    }
 else {
      textResult=(TextResult)resultValue;
    }
  }
  final HttpServletResponse response=actionRequest.getHttpServletResponse();
  String encoding=response.getCharacterEncoding();
  if (encoding == null) {
    encoding=madvocEncoding.getEncoding();
  }
  response.setContentType(textResult.contentType());
  response.setCharacterEncoding(encoding);
  response.setStatus(textResult.status());
  String text=textResult.value();
  if (text == null) {
    text=StringPool.EMPTY;
  }
  final byte[] data=text.getBytes(encoding);
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
