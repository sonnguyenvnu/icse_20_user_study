/** 
 * Given a class, the modifiers of on of it's member and the class that is trying to access that member, returns true is the member is accessible from the accessingClass Class.
 * @param classWithMember The Class with the member.
 * @param modifiers       The modifiers of that member.
 * @param accessingClass  The Class trying to access the member.
 * @return True if the member is visible from the accessingClass Class.
 */
public static boolean isMemberVisibleFromClass(Class<?> classWithMember,int modifiers,Class<?> accessingClass){
  if (accessingClass == null) {
    return false;
  }
  if (Modifier.isPublic(modifiers)) {
    return true;
  }
  boolean areInTheSamePackage=false;
  if (accessingClass.getPackage() != null) {
    areInTheSamePackage=accessingClass.getPackage().getName().equals(classWithMember.getPackage().getName());
  }
  if (Modifier.isProtected(modifiers)) {
    if (areInTheSamePackage || classWithMember.isAssignableFrom(accessingClass)) {
      return true;
    }
  }
 else   if (Modifier.isPrivate(modifiers)) {
    if (classWithMember.equals(accessingClass)) {
      return true;
    }
  }
 else   if (areInTheSamePackage) {
    return true;
  }
  return false;
}
