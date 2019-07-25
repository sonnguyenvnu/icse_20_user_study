@Override public void execute(IngestDocument document){
  if (overrideEnabled || document.hasField(field) == false || document.getFieldValue(field,Object.class) == null) {
    document.setFieldValue(field,value);
  }
}
