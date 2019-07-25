@Override public Iterator<DocumentField> iterator(){
  if (fields == null) {
    return Collections.emptyIterator();
  }
  return fields.values().iterator();
}
