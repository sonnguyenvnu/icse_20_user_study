public void reader(BytesReader<T> reader){
  checkNonMarshallableEnum(reader.getClass());
  this.reader=new BytesAsSizedReader<>(reader);
}
