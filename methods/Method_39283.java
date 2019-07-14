private void useAndOpenFolderIfNotSet(){
  if (folder == null) {
    if (folderName != null) {
      useFolder(folderName);
    }
 else {
      useDefaultFolder();
    }
  }
}
