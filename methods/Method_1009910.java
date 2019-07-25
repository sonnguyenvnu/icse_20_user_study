@Override public final API every(int n){
  if (wasForced()) {
    return api();
  }
  if (n <= 0) {
    throw new IllegalArgumentException("rate limit count must be positive");
  }
  if (n > 1) {
    addMetadata(Key.LOG_EVERY_N,n);
  }
  return api();
}
