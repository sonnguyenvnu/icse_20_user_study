private void copyLibraryPathEntries(File macOSDirectory) throws IOException {
  for (  FileSet fileSet : libraryPath) {
    File libraryPathDirectory=fileSet.getDir();
    DirectoryScanner directoryScanner=fileSet.getDirectoryScanner(getProject());
    String[] includedFiles=directoryScanner.getIncludedFiles();
    for (    String includedFile : includedFiles) {
      File source=new File(libraryPathDirectory,includedFile);
      File destination=new File(macOSDirectory,new File(includedFile).getName());
      copy(source,destination);
    }
  }
}
