public void writer(BytesWriter<? super T> writer){
  checkNonMarshallableEnum(writer.getClass());
  dataAccess(new ExternalBytesMarshallableDataAccess<>(tClass,reader,writer));
}
