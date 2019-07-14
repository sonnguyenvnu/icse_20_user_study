@Override public HmilyTransaction findById(final String id){
  String fullFileName=RepositoryPathUtils.getFullFileName(filePath,id);
  File file=new File(fullFileName);
  try {
    return readTransaction(file);
  }
 catch (  Exception e) {
    e.printStackTrace();
    return null;
  }
}
