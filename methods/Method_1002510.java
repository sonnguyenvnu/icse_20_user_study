@Override public byte[] deflate(InputStream data) throws CompressionException {
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  try (SnappyFramedOutputStream snappy=new SnappyFramedOutputStream(out)){
    IOUtils.copy(data,snappy);
  }
 catch (  IOException e) {
    throw new CompressionException(CompressionConstants.DECODING_ERROR + getContentEncodingName(),e);
  }
  return out.toByteArray();
}
