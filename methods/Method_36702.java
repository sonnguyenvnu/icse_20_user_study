@Override public String type(T gen){
  if (mMap.containsKey(gen)) {
    return mMap.get(gen);
  }
  return UNKNOWN;
}
