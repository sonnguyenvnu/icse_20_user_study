/** 
 * Replaces type with mapped type for current path.
 */
protected Class replaceWithMappedTypeForPath(final Class target){
  if (mappings == null) {
    return target;
  }
  Class newType;
  Path altPath=path.getAltPath();
  if (altPath != null) {
    if (!altPath.equals(path)) {
      newType=mappings.get(altPath);
      if (newType != null) {
        return newType;
      }
    }
  }
  newType=mappings.get(path);
  if (newType != null) {
    return newType;
  }
  return target;
}
