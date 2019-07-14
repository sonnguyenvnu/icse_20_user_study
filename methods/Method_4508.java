@Override protected FlacDecoderException createUnexpectedDecodeException(Throwable error){
  return new FlacDecoderException("Unexpected decode error",error);
}
