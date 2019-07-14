@Override protected FfmpegDecoderException createUnexpectedDecodeException(Throwable error){
  return new FfmpegDecoderException("Unexpected decode error",error);
}
