@Override public boolean containsHeader(String key){
  return header(key).isPresent();
}
