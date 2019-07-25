@Override public ValueReader<T> copy(){
  return new ValueReader<>(valueType);
}
