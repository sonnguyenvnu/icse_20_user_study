@Override public boolean hasWriteTime(){
  return expiresAfterWrite() || refreshAfterWrite();
}
