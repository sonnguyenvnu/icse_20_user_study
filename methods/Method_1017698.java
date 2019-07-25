@Override public Generator<T> copy(){
  return new Fields<>(types().get(0));
}
