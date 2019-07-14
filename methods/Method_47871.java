@Override @NonNull public List<Score> toList(){
  computeAll();
  return new LinkedList<>(list);
}
