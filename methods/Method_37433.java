/** 
 * Returns the  {@link BCrypt} hash tool with given salt.
 */
public static HashEngine bcrypt(final String salt){
  return new HashEngine(){
    @Override public String hash(    final String input){
      return BCrypt.hashpw(input,salt);
    }
    @Override public boolean check(    final String input,    final String hash){
      return BCrypt.checkpw(input,hash);
    }
  }
;
}
