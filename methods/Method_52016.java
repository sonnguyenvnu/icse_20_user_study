@Override public boolean covers(T sig){
  return visMask.contains(sig.visibility);
}
