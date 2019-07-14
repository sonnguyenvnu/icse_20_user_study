@RequiresApi(Build.VERSION_CODES.LOLLIPOP) private static int getNativeFileDescriptor(FileDescriptor fileDescriptor,boolean closeOriginalDescriptor) throws GifIOException, ErrnoException {
  try {
    final int nativeFileDescriptor=createTempNativeFileDescriptor();
    Os.dup2(fileDescriptor,nativeFileDescriptor);
    return nativeFileDescriptor;
  }
  finally {
    if (closeOriginalDescriptor) {
      Os.close(fileDescriptor);
    }
  }
}
