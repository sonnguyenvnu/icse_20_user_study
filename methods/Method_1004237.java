@Override public DataAccess<T> copy(){
  return new ValueDataAccess<>(valueType);
}
