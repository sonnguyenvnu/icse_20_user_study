static GifIOException fromCode(final int nativeErrorCode){
  if (nativeErrorCode == GifError.NO_ERROR.errorCode) {
    return null;
  }
  return new GifIOException(nativeErrorCode,null);
}
