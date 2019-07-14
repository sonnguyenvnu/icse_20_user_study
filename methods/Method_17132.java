long getRefreshAfterWriteNanos(){
  return refreshes() ? refreshNanos : DEFAULT_REFRESH_NANOS;
}
