private SavedField obtainSaveField(){
  SavedField[] savedFields=iSavedFields;
  int savedFieldsCount=iSavedFieldsCount;
  if (savedFieldsCount == savedFields.length || iSavedFieldsShared) {
    SavedField[] newArray=new SavedField[savedFieldsCount == savedFields.length ? savedFieldsCount * 2 : savedFields.length];
    System.arraycopy(savedFields,0,newArray,0,savedFieldsCount);
    iSavedFields=savedFields=newArray;
    iSavedFieldsShared=false;
  }
  iSavedState=null;
  SavedField saved=savedFields[savedFieldsCount];
  if (saved == null) {
    saved=savedFields[savedFieldsCount]=new SavedField();
  }
  iSavedFieldsCount=savedFieldsCount + 1;
  return saved;
}
