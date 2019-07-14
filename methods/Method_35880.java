public Set<String> keys(){
  return newHashSet(transform(headers.keySet(),toStringFunction()));
}
