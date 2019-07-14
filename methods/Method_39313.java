/** 
 * Returns new  {@link MimeBodyPart} with content set as msgMultipart.
 * @param msgMultipart {@link MimeMultipart} to add to the new {@link MimeBodyPart}.
 * @return new {@link MimeBodyPart} with content set as msgMultipart.
 * @throws MessagingException if there is a failure.
 */
private MimeBodyPart getBaseBodyPart(final MimeMultipart msgMultipart) throws MessagingException {
  final MimeBodyPart bodyPart=new MimeBodyPart();
  bodyPart.setContent(msgMultipart);
  return bodyPart;
}
