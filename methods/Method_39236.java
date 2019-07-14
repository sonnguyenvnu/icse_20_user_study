/** 
 * Creates  {@link EmailAttachment}.
 * @return {@link EmailAttachment}.
 * @throws MailException if issue with {@link DataSource}.
 */
public EmailAttachment<FileDataSource> buildFileDataSource(final String messageId,final File attachmentStorage) throws MailException {
  try {
    final FileDataSource fds;
    if (dataSource instanceof FileDataSource) {
      fds=(FileDataSource)dataSource;
    }
 else {
      final File file=new File(attachmentStorage,messageId);
      FileUtil.writeStream(file,dataSource.getInputStream());
      fds=new FileDataSource(file);
    }
    checkDataSource();
    return new EmailAttachment<>(name,contentId,isInline,fds).setEmbeddedMessage(targetMessage);
  }
 catch (  final IOException ioexc) {
    throw new MailException(ioexc);
  }
}
