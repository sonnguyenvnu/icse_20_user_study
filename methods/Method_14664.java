@JsonIgnore public void setImportOptionMetadata(ArrayNode jsonArray){
  _importOptionMetadata=jsonArray;
  updateModified();
}
