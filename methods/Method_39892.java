/** 
 * Reads HTTP request body using the request stream. Once body is read, it cannot be read again!
 */
public static String readRequestBodyFromStream(final HttpServletRequest request) throws IOException {
  String charEncoding=request.getCharacterEncoding();
  if (charEncoding == null) {
    charEncoding=JoddCore.encoding;
  }
  CharArrayWriter charArrayWriter=new CharArrayWriter();
  BufferedReader bufferedReader=null;
  try {
    InputStream inputStream=request.getInputStream();
    if (inputStream != null) {
      bufferedReader=new BufferedReader(new InputStreamReader(inputStream,charEncoding));
      StreamUtil.copy(bufferedReader,charArrayWriter);
    }
 else {
      return StringPool.EMPTY;
    }
  }
  finally {
    StreamUtil.close(bufferedReader);
  }
  return charArrayWriter.toString();
}
