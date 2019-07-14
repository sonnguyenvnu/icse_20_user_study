/** 
 * Correctly resolves file name from the message part. Thanx to: Flavio Pompermaier
 * @param part {@link Part} to decode file name from.
 * @return String containing file name.
 */
public static String resolveFileName(final Part part) throws MessagingException {
  if (!(part instanceof MimeBodyPart)) {
    return part.getFileName();
  }
  final String contentType=part.getContentType();
  String ret;
  try {
    ret=MimeUtility.decodeText(part.getFileName());
  }
 catch (  final Exception ex) {
    final String contentId=((MimeBodyPart)part).getContentID();
    if (contentId != null) {
      ret=contentId + contentTypeForFileName(contentType);
    }
 else {
      ret=defaultFileName(contentType);
    }
  }
  return ret;
}
