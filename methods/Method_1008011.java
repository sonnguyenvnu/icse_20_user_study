@Override public void execute(IngestDocument document){
  String oldVal=document.getFieldValue(field,String.class,ignoreMissing);
  if (oldVal == null && ignoreMissing) {
    return;
  }
 else   if (oldVal == null) {
    throw new IllegalArgumentException("field [" + field + "] is null, cannot split.");
  }
  String[] strings=oldVal.split(separator);
  List<String> splitList=new ArrayList<>(strings.length);
  Collections.addAll(splitList,strings);
  document.setFieldValue(targetField,splitList);
}
