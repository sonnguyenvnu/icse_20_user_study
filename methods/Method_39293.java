/** 
 * Updates the email flags on the server.
 */
public void updateEmailFlags(final ReceivedEmail receivedEmail){
  useAndOpenFolderIfNotSet();
  try {
    folder.setFlags(new int[]{receivedEmail.messageNumber()},receivedEmail.flags(),true);
  }
 catch (  MessagingException mex) {
    throw new MailException("Failed to fetch messages",mex);
  }
}
