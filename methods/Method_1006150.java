@Override public List<FieldChange> cleanup(BibEntry entry){
  Optional<String> oldValue=entry.getField(FieldName.FILE);
  if (!oldValue.isPresent()) {
    return Collections.emptyList();
  }
  List<LinkedFile> fileList=entry.getFiles();
  String newValue=FileFieldWriter.getStringRepresentation(fileList);
  if (!oldValue.get().equals(newValue)) {
    entry.setField(FieldName.FILE,newValue);
    FieldChange change=new FieldChange(entry,FieldName.FILE,oldValue.get(),newValue);
    return Collections.singletonList(change);
  }
  return Collections.emptyList();
}
