@Override public int remove(final String id){
  String fullFileName=RepositoryPathUtils.getFullFileName(filePath,id);
  File file=new File(fullFileName);
  if (file.exists()) {
    file.delete();
  }
  return ROWS;
}
