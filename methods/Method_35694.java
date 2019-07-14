private void assertExistsAndIsDirectory(){
  if (rootDirectory.exists() && !rootDirectory.isDirectory()) {
    throw new RuntimeException(rootDirectory + " is not a directory");
  }
 else   if (!rootDirectory.exists()) {
    throw new RuntimeException(rootDirectory + " does not exist");
  }
}
