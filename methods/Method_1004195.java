public static void msync(long addr,long length) throws IOException {
  if (msync(new Pointer(addr),new size_t(length),MS_SYNC) == -1)   throw new IOException("msync failed: error code " + Native.getLastError());
}
