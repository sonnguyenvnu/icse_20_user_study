/** 
 * Convert from array of  {@link EmailAddress} to array of {@link InternetAddress}.
 * @param addresses {@link EmailMessage}
 * @return array of {@link InternetAddress}. Returns empty array if addresses was  {@code null}.
 * @throws MessagingException if there are failures
 */
public static InternetAddress[] convert(final EmailAddress[] addresses) throws MessagingException {
  if (addresses == null) {
    return new InternetAddress[0];
  }
  final int numRecipients=addresses.length;
  final InternetAddress[] address=new InternetAddress[numRecipients];
  for (int i=0; i < numRecipients; i++) {
    address[i]=addresses[i].toInternetAddress();
  }
  return address;
}
