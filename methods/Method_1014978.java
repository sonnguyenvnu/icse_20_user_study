public FieldUtil set(String fieldName,String value){
  if (null == value) {
    this.getFixedField(fieldName,"");
  }
 else {
    this.getFixedField(fieldName,value);
  }
  return this;
}
