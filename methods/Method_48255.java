public int determineTTL(){
  return hasDeletions() ? 0 : determineTTL(getAdditions());
}
