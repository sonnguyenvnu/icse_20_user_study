@Override public <T>T getColumnAs(Factory<T> factory){
  return super.as(factory,0,valuePosition);
}
