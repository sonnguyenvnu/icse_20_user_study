@Override public Iterator<List<String>> iterator(){
  if (!finishedAdding) {
    throw new IllegalStateException("Container must be flipped before reading the data");
  }
  return renderRows.iterator();
}
