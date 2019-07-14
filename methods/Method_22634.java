protected String getLibraryFolder() throws FileNotFoundException {
  return FileManager.findFolder(kUserDomain,kDomainLibraryFolderType);
}
