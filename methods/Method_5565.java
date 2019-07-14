@Override protected final SubtitleDecoderException createUnexpectedDecodeException(Throwable error){
  return new SubtitleDecoderException("Unexpected decode error",error);
}
