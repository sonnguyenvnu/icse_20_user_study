public boolean contains(O data){
  if (data == null) {
    throw new IllegalArgumentException();
  }
  for (int i=0; i < Math.max(positive.size(),negative.size()); i++) {
    if (i < positive.size() && data == positive.get(i)) {
      return true;
    }
    if (i < negative.size() && data == negative.get(i)) {
      return true;
    }
  }
  return false;
}
