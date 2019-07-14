public Set<String> keySet(){
  if (table != null) {
    return table.keySet();
  }
 else {
    return Collections.emptySet();
  }
}
