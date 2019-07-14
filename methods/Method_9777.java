/** 
 * Checks password, length [1, 16].
 * @param password the specific password
 * @return {@code true} if it is invalid, returns {@code false} otherwise
 */
public static boolean invalidUserPassword(final String password){
  return password.length() < MIN_PWD_LENGTH || password.length() > MAX_PWD_LENGTH;
}
