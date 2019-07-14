/** 
 * Suppress access check against a reflection object. SecurityException is silently ignored. Checks first if the object is already accessible.
 */
public static void forceAccess(final AccessibleObject accObject){
  try {
    if (System.getSecurityManager() == null)     accObject.setAccessible(true);
 else {
      AccessController.doPrivileged((PrivilegedAction)() -> {
        accObject.setAccessible(true);
        return null;
      }
);
    }
  }
 catch (  SecurityException sex) {
  }
}
