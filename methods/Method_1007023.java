@Override public void write(List<? extends T> items) throws Exception {
  int current=counter;
  counter+=items.size();
  if (current < 3 && (counter >= 2 || counter >= 3)) {
    throw new IllegalStateException("Temporary error");
  }
}
