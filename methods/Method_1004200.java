private void msync(long address,long length) throws IOException {
  if (OS.pageAlign(address) != address) {
    long oldAddress=address;
    address=OS.pageAlign(address) - OS.pageSize();
    length+=oldAddress - address;
  }
  if (OS.isWindows()) {
    WindowsMsync.msync(raf,address,length);
  }
 else {
    PosixMsync.msync(address,length);
  }
}
