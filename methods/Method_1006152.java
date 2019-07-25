@Override public List<FieldChange> cleanup(BibEntry entry){
  List<LinkedFile> fileList=entry.getFiles();
  List<LinkedFile> newFileList=new ArrayList<>();
  boolean changed=false;
  for (  LinkedFile fileEntry : fileList) {
    String oldFileName=fileEntry.getLink();
    String newFileName=FileUtil.relativize(Paths.get(oldFileName),databaseContext.getFileDirectoriesAsPaths(filePreferences)).toString();
    LinkedFile newFileEntry=fileEntry;
    if (!oldFileName.equals(newFileName)) {
      newFileEntry=new LinkedFile(fileEntry.getDescription(),newFileName,fileEntry.getFileType());
      changed=true;
    }
    newFileList.add(newFileEntry);
  }
  if (changed) {
    Optional<FieldChange> change=entry.setFiles(newFileList);
    if (change.isPresent()) {
      return Collections.singletonList(change.get());
    }
 else {
      return Collections.emptyList();
    }
  }
  return Collections.emptyList();
}
