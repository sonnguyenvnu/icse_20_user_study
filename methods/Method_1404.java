public CloseableReference<PooledByteBuffer> generate(short width,short height){
  PooledByteBufferOutputStream os=null;
  try {
    os=mPooledByteBufferFactory.newOutputStream(EMPTY_JPEG_PREFIX.length + EMPTY_JPEG_SUFFIX.length + 4);
    os.write(EMPTY_JPEG_PREFIX);
    os.write((byte)(height >> 8));
    os.write((byte)(height & 0x00ff));
    os.write((byte)(width >> 8));
    os.write((byte)(width & 0x00ff));
    os.write(EMPTY_JPEG_SUFFIX);
    return CloseableReference.of(os.toByteBuffer());
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
 finally {
    if (os != null) {
      os.close();
    }
  }
}
