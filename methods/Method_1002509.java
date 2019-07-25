@Override public byte[] inflate(InputStream data) throws CompressionException {
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  try (SnappyFramedInputStream snappy=new SnappyFramedInputStream(data,true)){
    IOUtils.copy(snappy,out);
  }
 catch (  IOException e) {
    throw new CompressionException(CompressionConstants.DECODING_ERROR + getContentEncodingName(),e);
  }
  return out.toByteArray();
}
