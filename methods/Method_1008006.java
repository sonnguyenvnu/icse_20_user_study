@Override public void execute(IngestDocument document){
  List<?> list=document.getFieldValue(field,List.class);
  if (list == null) {
    throw new IllegalArgumentException("field [" + field + "] is null, cannot join.");
  }
  String joined=list.stream().map(Object::toString).collect(Collectors.joining(separator));
  document.setFieldValue(targetField,joined);
}
