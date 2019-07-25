@Override public void execute(IngestDocument document){
  Object oldValue=document.getFieldValue(field,Object.class,ignoreMissing);
  Object newValue;
  if (oldValue == null && ignoreMissing) {
    return;
  }
 else   if (oldValue == null) {
    throw new IllegalArgumentException("Field [" + field + "] is null, cannot be converted to type [" + convertType + "]");
  }
  if (oldValue instanceof List) {
    List<?> list=(List<?>)oldValue;
    List<Object> newList=new ArrayList<>(list.size());
    for (    Object value : list) {
      newList.add(convertType.convert(value));
    }
    newValue=newList;
  }
 else {
    newValue=convertType.convert(oldValue);
  }
  document.setFieldValue(targetField,newValue);
}
