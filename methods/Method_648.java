public List<Object> getObjectFieldValues(Object object) throws Exception {
  List<Object> fieldValues=new ArrayList<Object>(sortedGetters.length);
  for (  FieldSerializer getter : sortedGetters) {
    Class fieldClass=getter.fieldInfo.fieldClass;
    if (fieldClass.isPrimitive()) {
      continue;
    }
    if (fieldClass.getName().startsWith("java.lang.")) {
      continue;
    }
    fieldValues.add(getter.getPropertyValue(object));
  }
  return fieldValues;
}
