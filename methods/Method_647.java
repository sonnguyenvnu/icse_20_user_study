public List<Object> getFieldValues(Object object) throws Exception {
  List<Object> fieldValues=new ArrayList<Object>(sortedGetters.length);
  for (  FieldSerializer getter : sortedGetters) {
    fieldValues.add(getter.getPropertyValue(object));
  }
  return fieldValues;
}
