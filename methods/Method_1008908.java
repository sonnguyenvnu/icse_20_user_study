@Override public List<Object> apply(Object o){
  if (o instanceof CTSimpleField) {
    simpleFields.add((CTSimpleField)o);
  }
  return null;
}
