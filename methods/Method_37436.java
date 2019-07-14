/** 
 * Validates a password using a hash.
 * @param password the password to check
 * @param goodHash the hash of the valid password
 * @return true if the password is correct, false if not
 */
public boolean validatePassword(final char[] password,final String goodHash){
  String[] params=goodHash.split(":");
  int iterations=Integer.parseInt(params[ITERATION_INDEX]);
  byte[] salt=fromHex(params[SALT_INDEX]);
  byte[] hash=fromHex(params[PBKDF2_INDEX]);
  byte[] testHash=pbkdf2(password,salt,iterations,hash.length);
  return slowEquals(hash,testHash);
}
