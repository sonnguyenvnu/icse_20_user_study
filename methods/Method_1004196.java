public static void msync(RandomAccessFile raf,long addr,long length) throws IOException {
  int retry=0;
  boolean success;
  int lastError=0;
  do {
    success=KERNEL_32.FlushViewOfFile(new Pointer(addr),new SIZE_T(length));
    if (success || (lastError=KERNEL_32.GetLastError()) != ERROR_LOCK_VIOLATION)     break;
    retry++;
  }
 while (retry < 3);
  if (success) {
    raf.getChannel().force(false);
  }
 else {
    throw new IOException(Kernel32Util.formatMessageFromLastErrorCode(lastError));
  }
}
