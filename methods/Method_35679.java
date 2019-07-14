private File writableFileFor(String name){
  assertExistsAndIsDirectory();
  assertFilePathIsUnderRoot(name);
  assertWritable();
  final File filePath=new File(name);
  if (filePath.isAbsolute()) {
    return filePath;
  }
 else {
    return new File(rootDirectory,name);
  }
}
