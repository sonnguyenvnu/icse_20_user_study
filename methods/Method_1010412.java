@Override public Iterable<IResource> output(){
  if (MapSequence.fromMap(results).isEmpty()) {
    return null;
  }
  return Sequence.fromIterable(MapSequence.fromMap(results).values()).last().output();
}
