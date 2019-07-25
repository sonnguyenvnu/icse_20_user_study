public void writer(SizedWriter<? super T> writer){
  checkNonMarshallableEnum(writer.getClass());
  dataAccess(new SizedMarshallableDataAccess<>(tClass,reader,writer));
}
