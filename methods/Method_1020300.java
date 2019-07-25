private boolean exist(Layer l){
  if (l == null) {
    return false;
  }
  for (int i=nlayers; --i >= 0; ) {
    if (component[i] == l) {
      return true;
    }
  }
  return false;
}
