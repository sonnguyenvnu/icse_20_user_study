/** 
 * Returns the number of deleted messages.
 * @return The number of deleted messages.
 */
public int getDeletedMessageCount(){
  useAndOpenFolderIfNotSet();
  try {
    return folder.getDeletedMessageCount();
  }
 catch (  final MessagingException msgexc) {
    throw new MailException(msgexc);
  }
}
