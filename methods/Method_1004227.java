public void reader(SizedReader<T> reader){
  checkNonMarshallableEnum(reader.getClass());
  this.reader=reader;
}
