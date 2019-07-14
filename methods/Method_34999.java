boolean usesAccessQueue(){
  return expiresAfterAccess() || evictsBySize();
}
