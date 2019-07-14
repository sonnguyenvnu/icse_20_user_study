@Override protected OpusDecoderException createUnexpectedDecodeException(Throwable error){
  return new OpusDecoderException("Unexpected decode error",error);
}
