@Override public void createIfNecessary(){
  assertWritable();
  if (rootDirectory.exists() && rootDirectory.isFile()) {
    throw new IllegalStateException(rootDirectory + " already exists and is a file");
  }
 else   if (!rootDirectory.exists()) {
    rootDirectory.mkdirs();
  }
}
