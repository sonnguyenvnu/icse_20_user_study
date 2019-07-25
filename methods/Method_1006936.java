private FieldSet enhance(DefaultFieldSet fieldSet){
  if (dateFormat != null) {
    fieldSet.setDateFormat(dateFormat);
  }
  if (numberFormat != null) {
    fieldSet.setNumberFormat(numberFormat);
  }
  return fieldSet;
}
