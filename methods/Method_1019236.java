@Override public void close() throws IOException {
  try {
    if (mLock != null) {
      mLock.release();
    }
  }
  finally {
    mLockFileOutputStream.close();
  }
}
