/** 
 * Creates new  {@link InternetAddress} from current data.
 * @return {@link InternetAddress} from current data.
 */
public InternetAddress toInternetAddress() throws AddressException {
  try {
    return new InternetAddress(email,personalName,JoddCore.encoding);
  }
 catch (  final UnsupportedEncodingException ueex) {
    throw new AddressException(ueex.toString());
  }
}
