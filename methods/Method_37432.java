/** 
 * Returns the  {@link BCrypt} hash tool with given rounds number for salt generation.
 */
public static HashEngine bcrypt(final int rounds){
  return bcrypt(BCrypt.gensalt(rounds));
}
