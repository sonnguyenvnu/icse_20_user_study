@Override public DataAccess<T> copy(){
  return new ExternalizableDataAccess<>(tClass,bytes.realCapacity());
}
