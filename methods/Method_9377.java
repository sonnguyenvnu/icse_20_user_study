private void checkFieldForError(EditTextBoldCursor field,String key,Editable s,boolean document){
  String value;
  if (errorsValues != null && (value=errorsValues.get(key)) != null) {
    if (TextUtils.equals(value,s)) {
      if (fieldsErrors != null && (value=fieldsErrors.get(key)) != null) {
        field.setErrorText(value);
      }
 else       if (documentsErrors != null && (value=documentsErrors.get(key)) != null) {
        field.setErrorText(value);
      }
    }
 else {
      field.setErrorText(null);
    }
  }
 else {
    field.setErrorText(null);
  }
  String errorKey=document ? "error_document_all" : "error_all";
  if (errorsValues != null && errorsValues.containsKey(errorKey)) {
    errorsValues.remove(errorKey);
    checkTopErrorCell(false);
  }
}
