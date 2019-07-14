/** 
 * Returns <code>true</code> if the first member is accessible from second one.
 */
public static boolean isAssignableFrom(final Member member1,final Member member2){
  return member1.getDeclaringClass().isAssignableFrom(member2.getDeclaringClass());
}
