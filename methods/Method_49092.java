@Override protected Admin<Vertex> processNextStart() throws NoSuchElementException {
  Admin<Vertex> start=this.starts.next();
  if (!cachedStarts.contains(start)) {
    if (cachedStartsAccessed) {
      cachedStarts.clear();
      cachedStartsAccessed=false;
    }
    final List<Traverser.Admin<Vertex>> newStarters=new ArrayList<>();
    starts.forEachRemaining(s -> {
      newStarters.add(s);
      cachedStarts.add(s);
    }
);
    starts.add(newStarters.iterator());
    cachedStarts.add(start);
  }
  return start;
}
