/** 
 * Safe version of <code>isAssignableFrom</code> method that returns <code>false</code> if one of the arguments is <code>null</code>.
 */
public static boolean isTypeOf(final Class<?> lookupClass,final Class<?> targetClass){
  if (targetClass == null || lookupClass == null) {
    return false;
  }
  return targetClass.isAssignableFrom(lookupClass);
}
