/** 
 * Checks whether the specified name is invalid. <p> A valid user name: <ul> <li>length [1, 64]</li> <li>content {a-z, A-Z, 0-9, -}</li> </ul> </p>
 * @param name the specified name
 * @return {@code true} if it is invalid, returns {@code false} otherwise
 */
public static boolean invalidUserName(final String name){
  if (StringUtils.isBlank(name)) {
    return true;
  }
  if (UserExt.isReservedUserName(name)) {
    return true;
  }
  final int length=name.length();
  if (length < MIN_USER_NAME_LENGTH || length > MAX_USER_NAME_LENGTH) {
    return true;
  }
  char c;
  for (int i=0; i < length; i++) {
    c=name.charAt(i);
    if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || '-' == c) {
      continue;
    }
    return true;
  }
  return false;
}
