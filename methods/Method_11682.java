public static Set<Field> getFieldsIncludeSuperClass(Class clazz){
  Set<Field> fields=new LinkedHashSet<Field>();
  Class current=clazz;
  while (current != null) {
    Field[] currentFields=current.getDeclaredFields();
    for (    Field currentField : currentFields) {
      fields.add(currentField);
    }
    current=current.getSuperclass();
  }
  return fields;
}
