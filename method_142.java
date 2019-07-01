/** 
 * Returns whether a  {@link Member} is accessible.
 * @param m Member to check
 * @return {@code true} if <code>m</code> is accessible
 */
static boolean _XXXXX_(final Member m){
  return m != null && Modifier.isPublic(m.getModifiers()) && !m.isSynthetic();
}