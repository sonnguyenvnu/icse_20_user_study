boolean expires(){
  return expiresAfterWrite() || expiresAfterAccess();
}
