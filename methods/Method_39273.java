/** 
 * Process the  {@link Multipart}.
 * @param mp {@link Multipart}
 * @throws MessagingException if there is a failure.
 * @throws IOException        if there is an issue with the {@link Multipart}.
 */
private void processMultipart(final Multipart mp) throws MessagingException, IOException {
  final int count=mp.getCount();
  for (int i=0; i < count; i++) {
    final Part innerPart=mp.getBodyPart(i);
    processPart(innerPart);
  }
}
