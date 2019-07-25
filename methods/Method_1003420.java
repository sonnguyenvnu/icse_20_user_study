private void cat(String fileName,long length){
  if (!FileUtils.exists(fileName)) {
    print("No such file: " + fileName);
  }
  if (FileUtils.isDirectory(fileName)) {
    print("Is a directory: " + fileName);
  }
  InputStream inFile=null;
  try {
    inFile=FileUtils.newInputStream(fileName);
    IOUtils.copy(inFile,out,length);
  }
 catch (  IOException e) {
    error(e);
  }
 finally {
    IOUtils.closeSilently(inFile);
  }
  println("");
}
