private void assertFilePathIsUnderRoot(String path){
  try {
    String rootPath=rootDirectory.getCanonicalPath();
    File file=new File(path);
    String filePath=file.isAbsolute() ? new File(path).getCanonicalPath() : new File(rootDirectory,path).getCanonicalPath();
    if (!filePath.startsWith(rootPath)) {
      throw new NotAuthorisedException("Access to file " + path + " is not permitted");
    }
  }
 catch (  IOException ioe) {
    throw new NotAuthorisedException("File " + path + " cannot be accessed",ioe);
  }
}
