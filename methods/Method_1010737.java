public Iterator<EditorCell> iterator(EditorCell anchor,boolean forward){
  return new UnmodifiableIterator<>(getVisibleChildCells().iterator(anchor,forward));
}
