@Override public boolean isOrderPreservingDatatype(Class<?> datatype){
  return (getSerializer(datatype) instanceof OrderPreservingSerializer);
}
