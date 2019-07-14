/** 
 * Process part of the received message. All parts are simply added to the  {@link ReceivedEmail}, i.e. hierarchy is not saved.
 * @param part {@link Part} of received message
 * @throws IOException        if there is an error with the content.
 * @throws MessagingException if there is an error.
 */
protected void processPart(final Part part) throws MessagingException, IOException {
  final Object content=part.getContent();
  if (content instanceof String) {
    addStringContent(part,(String)content);
  }
 else   if (content instanceof Multipart) {
    processMultipart((Multipart)content);
  }
 else   if (content instanceof InputStream) {
    addAttachment(part,(InputStream)content,attachmentStorage);
  }
 else   if (content instanceof MimeMessage) {
    final MimeMessage mimeMessage=(MimeMessage)content;
    attachedMessage(new ReceivedEmail(mimeMessage,false,attachmentStorage));
  }
 else {
    addAttachment(part,part.getInputStream(),attachmentStorage);
  }
}
