@Override public Boolean _XXXXX_() throws ArchivaRestServiceException {
  List<FileMetadata> fileMetadatas=new ArrayList<>(getSessionFileMetadatas());
  for (  FileMetadata fileMetadata : fileMetadatas) {
    deleteFile(Paths.get(fileMetadata.getServerFileName()).toString());
  }
  getSessionFileMetadatas().clear();
  return Boolean.TRUE;
}