@NonNull private String readFully() throws IOException {
  final Reader input=new InputStreamReader(inputStream);
  try {
    final StringWriter output=new StringWriter();
    final char[] buffer=new char[ACRAConstants.DEFAULT_BUFFER_SIZE_IN_BYTES];
    int count;
    while ((count=input.read(buffer)) != -1) {
      output.write(buffer,0,count);
    }
    return output.toString();
  }
  finally {
    IOUtils.safeClose(input);
  }
}
