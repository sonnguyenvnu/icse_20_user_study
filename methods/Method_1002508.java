@Override public byte[] inflate(InputStream data) throws CompressionException {
  ByteArrayOutputStream out;
  GZIPInputStream gzip=null;
  try {
    out=new ByteArrayOutputStream();
    gzip=new GZIPInputStream(data);
    IOUtils.copy(gzip,out);
  }
 catch (  IOException e) {
    throw new CompressionException(CompressionConstants.DECODING_ERROR + getContentEncodingName(),e);
  }
 finally {
    if (gzip != null) {
      IOUtils.closeQuietly(gzip);
    }
  }
  return out.toByteArray();
}
