private IllegalArgumentException error(){
  return new IllegalArgumentException("Serializing objects of type [" + datatype + "] is not supported");
}
