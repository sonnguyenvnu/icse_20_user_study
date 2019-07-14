boolean recordsWrite(){
  return expiresAfterWrite() || refreshes();
}
