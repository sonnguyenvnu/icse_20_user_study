@Override public List<FieldChange> cleanup(BibEntry entry){
  List<FieldChange> changes=new ArrayList<>();
  String oldFileContent=entry.getField(FieldName.FILE).orElse(null);
  List<LinkedFile> fileList=new ArrayList<>(entry.getFiles());
  int oldItemCount=fileList.size();
  for (  Map.Entry<String,String> field : fields.entrySet()) {
    entry.getField(field.getKey()).ifPresent(o -> {
      if (o.trim().isEmpty()) {
        return;
      }
      File f=new File(o);
      LinkedFile flEntry=new LinkedFile(f.getName(),o,field.getValue());
      fileList.add(flEntry);
      entry.clearField(field.getKey());
      changes.add(new FieldChange(entry,field.getKey(),o,null));
    }
);
  }
  if (fileList.size() != oldItemCount) {
    String newValue=FileFieldWriter.getStringRepresentation(fileList);
    entry.setField(FieldName.FILE,newValue);
    changes.add(new FieldChange(entry,FieldName.FILE,oldFileContent,newValue));
  }
  return changes;
}
