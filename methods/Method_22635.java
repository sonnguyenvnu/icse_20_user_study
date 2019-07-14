protected String getDocumentsFolder() throws FileNotFoundException {
  return FileManager.findFolder(kUserDomain,kDocumentsFolderType);
}
