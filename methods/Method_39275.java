/** 
 * Returns  {@code true} if the {@link Part} is inline.
 * @param part {@link Part} to parse.
 * @return {@code true} if the {@link Part} is inline.
 * @throws MessagingException if there is a failure.
 */
protected static boolean parseInline(final Part part) throws MessagingException {
  if (part instanceof MimePart) {
    final String dispositionId=part.getDisposition();
    return dispositionId != null && dispositionId.equalsIgnoreCase("inline");
  }
  return false;
}
