@Override public void write(List<? extends T> items) throws Exception {
  for (  T item : items) {
    invokeDelegateMethodWithArgument(item);
  }
}
