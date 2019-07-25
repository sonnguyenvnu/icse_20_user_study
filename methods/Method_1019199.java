@Override public Iterator<O> iterator(){
  if (consumed) {
    throw new RuntimeException("Invalid repeated inputItr consumption.");
  }
  consumed=true;
  return this;
}
