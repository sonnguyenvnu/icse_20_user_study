/** 
 * Checks the specified email whether in a valid mail domain.
 * @param email the specified email
 * @return {@code true} if it is, returns {@code false} otherwise
 */
public static boolean isValidMailDomain(final String email){
  final String whitelistMailDomains=Symphonys.MAIL_DOMAINS;
  if (StringUtils.isBlank(whitelistMailDomains)) {
    return true;
  }
  final String domain=StringUtils.substringAfter(email,"@");
  return StringUtils.containsIgnoreCase(whitelistMailDomains,domain);
}
