/** 
 * Returns the Content-ID of this  {@link Part}. Returns  {@code null} if none present.
 * @param part {@link Part} the Part to parse.
 * @return String containing content ID.
 * @throws MessagingException if there is a failure.
 * @see MimePart#getContentID()
 */
protected static String parseContentId(final Part part) throws MessagingException {
  if (part instanceof MimePart) {
    final MimePart mp=(MimePart)part;
    return mp.getContentID();
  }
 else {
    return null;
  }
}
