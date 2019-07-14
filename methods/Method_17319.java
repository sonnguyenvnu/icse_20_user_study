@Override public <T>T unwrap(Class<T> clazz){
  if (!clazz.isInstance(this)) {
    throw new IllegalArgumentException("Class " + clazz + " is unknown to this implementation");
  }
  @SuppressWarnings("unchecked") T castedEntry=(T)this;
  return castedEntry;
}
