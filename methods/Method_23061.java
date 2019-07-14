private void copyClassPathEntries(File javaDirectory) throws IOException {
  for (  FileSet fileSet : classPath) {
    File classPathDirectory=fileSet.getDir();
    DirectoryScanner directoryScanner=fileSet.getDirectoryScanner(getProject());
    String[] includedFiles=directoryScanner.getIncludedFiles();
    for (    String includedFile : includedFiles) {
      File source=new File(classPathDirectory,includedFile);
      File destination=new File(javaDirectory,new File(includedFile).getName());
      copy(source,destination);
    }
  }
}
