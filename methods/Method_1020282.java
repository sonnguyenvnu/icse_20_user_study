public int open(String fileName) throws IOException {
  java.io.File file=getFile(fileName);
  if (!file.exists()) {
    file.createNewFile();
  }
  inputStream=new FileInputStream(file);
  outputStream=new FileOutputStream(file);
  return 1;
}
