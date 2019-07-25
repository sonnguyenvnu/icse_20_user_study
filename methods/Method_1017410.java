@Override public Iterator<MinimalField> iterator(){
  return Collections.unmodifiableList(fields).iterator();
}
