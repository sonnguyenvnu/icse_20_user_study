@Override @SneakyThrows public <T>Result<T> doImport(InputStream inputStream,Class<T> type,Function<T,Error> afterParsed,Class... group){
  AtomicInteger counter=new AtomicInteger(0);
  AtomicInteger errorCounter=new AtomicInteger(0);
  List<T> data=new ArrayList<>();
  List<Error> errors=new ArrayList<>();
  HeaderMapper headerMapper=getHeaderMapper(type,group);
  if (headerMapper == null) {
    throw new UnsupportedOperationException("????????");
  }
  ExcelIO.read(inputStream,row -> {
    counter.getAndAdd(1);
    Map<String,Object> mapValue=row.getResult();
    Map<String,Object> newValue=new HashMap<>();
    for (    Map.Entry<String,Object> entry : mapValue.entrySet()) {
      String key=entry.getKey();
      HeaderMapping mapping=headerMapper.getMapping(key).orElse(null);
      if (mapping == null || !mapping.enableImport) {
        continue;
      }
      Object value=mapping.getConverter().convertFromCell(entry.getValue());
      String field=mapping.getField();
      if (field.contains(".")) {
        String tmpField=field;
        Map<String,Object> nestMapValue=newValue;
        while (tmpField.contains(".")) {
          String[] nestFields=tmpField.split("[.]",2);
          String nestField=nestFields[0];
          tmpField=nestFields[1];
          Object nestValue=nestMapValue.get(nestField);
          if (nestValue == null) {
            nestMapValue.put(nestField,nestMapValue=new HashMap<>());
          }
 else {
            if (nestValue instanceof Map) {
              nestMapValue=((Map)nestValue);
            }
 else {
              nestMapValue.put(nestField,nestMapValue=FastBeanCopier.copy(nestValue,new HashMap<>()));
            }
          }
        }
        nestMapValue.put(tmpField,value);
      }
 else {
        newValue.put(field,value);
      }
    }
    T instance=FastBeanCopier.getBeanFactory().newInstance(type);
    FastBeanCopier.copy(newValue,instance);
    data.add(instance);
    Error error=afterParsed.apply(instance);
    if (null != error) {
      errorCounter.getAndAdd(1);
      error.setRowIndex(counter.get());
      error.setSheetIndex(row.getSheet());
      errors.add(error);
    }
  }
);
  return Result.<T>builder().data(data).errors(errors).success(counter.get() - errorCounter.get()).total(counter.get()).error(errorCounter.get()).build();
}
