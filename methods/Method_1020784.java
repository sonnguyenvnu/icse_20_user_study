/** 
 * Matches raw password and encoded password.
 * @param rawPassword     raw password
 * @param encodedPassword encoded password
 * @return match or not
 */
@Override public boolean matches(CharSequence rawPassword,String encodedPassword){
  String rawPwd=(String)rawPassword;
  return BCrypt.checkpw(rawPwd,encodedPassword);
}
