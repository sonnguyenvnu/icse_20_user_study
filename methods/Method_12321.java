private static long openFileDescriptor(FileDescriptor fileDescriptor,long offset,boolean closeOriginalDescriptor) throws GifIOException {
  final int nativeFileDescriptor;
  if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) {
    try {
      nativeFileDescriptor=getNativeFileDescriptor(fileDescriptor,closeOriginalDescriptor);
    }
 catch (    Exception e) {
      throw new GifIOException(GifError.OPEN_FAILED.errorCode,e.getMessage());
    }
  }
 else {
    nativeFileDescriptor=extractNativeFileDescriptor(fileDescriptor,closeOriginalDescriptor);
  }
  return openNativeFileDescriptor(nativeFileDescriptor,offset);
}
