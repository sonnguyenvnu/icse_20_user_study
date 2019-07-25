@Override public DataAccess<T> copy(){
  return new SerializableDataAccess<>(bytes.realCapacity());
}
