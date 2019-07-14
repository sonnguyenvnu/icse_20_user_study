/** 
 * Opens new folder and closes previously opened folder.
 * @param folderName Folder to open
 */
public void useFolder(final String folderName){
  closeFolderIfOpened(folder);
  try {
    this.folderName=folderName;
    this.folder=getService().getFolder(folderName);
    try {
      folder.open(Folder.READ_WRITE);
    }
 catch (    final MailException ignore) {
      folder.open(Folder.READ_ONLY);
    }
  }
 catch (  final MessagingException msgexc) {
    throw new MailException("Failed to connect to folder: " + folderName,msgexc);
  }
}
