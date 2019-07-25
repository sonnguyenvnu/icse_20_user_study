@Override public void execute(IngestDocument document){
  if (document.hasField(field,true) == false) {
    if (ignoreMissing) {
      return;
    }
 else {
      throw new IllegalArgumentException("field [" + field + "] doesn't exist");
    }
  }
  if (document.hasField(targetField,true)) {
    throw new IllegalArgumentException("field [" + targetField + "] already exists");
  }
  Object value=document.getFieldValue(field,Object.class);
  document.removeField(field);
  try {
    document.setFieldValue(targetField,value);
  }
 catch (  Exception e) {
    document.setFieldValue(field,value);
    throw e;
  }
}
