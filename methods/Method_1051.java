@Override public AnimatedImage decode(ByteBuffer byteBuffer){
  return WebPImage.create(byteBuffer);
}
