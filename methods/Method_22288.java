@NonNull private String readWithTimeout() throws IOException {
  final long until=System.currentTimeMillis() + timeout;
  try {
    final ByteArrayOutputStream output=new ByteArrayOutputStream();
    final byte[] buffer=new byte[ACRAConstants.DEFAULT_BUFFER_SIZE_IN_BYTES];
    int count;
    while ((count=fillBufferUntil(buffer,until)) != -1) {
      output.write(buffer,0,count);
    }
    return output.toString();
  }
  finally {
    IOUtils.safeClose(inputStream);
  }
}
