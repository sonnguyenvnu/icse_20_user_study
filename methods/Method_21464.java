protected Map<String,Object> createNullsSource(List<Field> secondTableReturnedFields){
  Map<String,Object> nulledSource=new HashMap<>();
  for (  Field field : secondTableReturnedFields) {
    if (!field.getName().equals("*")) {
      nulledSource.put(field.getName(),null);
    }
  }
  return nulledSource;
}
