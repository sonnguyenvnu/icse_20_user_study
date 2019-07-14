/** 
 * Creates  {@link EmailAttachmentBuilder} from {@link Part} and sets Content ID, inline and name.
 * @param part {@link Part}.
 * @return this
 * @see #attachment(EmailAttachment)
 */
private static EmailAttachmentBuilder addAttachmentInfo(final Part part) throws MessagingException {
  final String fileName=EmailUtil.resolveFileName(part);
  final String contentId=parseContentId(part);
  final boolean isInline=parseInline(part);
  return new EmailAttachmentBuilder().name(fileName).contentId(contentId).inline(isInline);
}
