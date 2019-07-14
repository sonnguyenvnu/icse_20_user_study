static private String getDocumentsPath() throws Exception {
  return Shell32Util.getSpecialFolderPath(ShlObj.CSIDL_MYDOCUMENTS,true);
}
