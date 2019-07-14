/** 
 * Return whether we can identify the typeName as a java.util collection class or interface as specified.
 * @param clazzType Class
 * @param includeInterfaces boolean
 * @return boolean
 */
public static boolean isCollectionType(Class<?> clazzType,boolean includeInterfaces){
  if (COLLECTION_CLASSES_BY_NAMES.contains(clazzType)) {
    return true;
  }
  return includeInterfaces && COLLECTION_INTERFACES_BY_NAMES.contains(clazzType);
}
