@NotNull @Override public Iterator<EditorCell> iterator(){
  return new UnmodifiableIterator<>(getVisibleChildCells().iterator());
}
