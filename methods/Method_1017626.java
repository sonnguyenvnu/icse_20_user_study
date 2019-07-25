public synchronized long length() throws SQLException {
  checkFreed();
  if (support64bit) {
    return getLo(false).size64();
  }
 else {
    return getLo(false).size();
  }
}
