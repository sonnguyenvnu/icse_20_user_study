long getExpiresAfterWriteNanos(){
  return expiresAfterWrite() ? expireAfterWriteNanos : DEFAULT_EXPIRATION_NANOS;
}
