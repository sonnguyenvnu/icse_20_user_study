/** 
 * Check whether two arrays of types match, converting primitive types to their corresponding wrappers.
 */
private boolean match(Class<?>[] declaredTypes,Class<?>[] actualTypes){
  if (declaredTypes.length == actualTypes.length) {
    for (int i=0; i < actualTypes.length; i++) {
      if (actualTypes[i] == NULL.class)       continue;
      if (wrapper(declaredTypes[i]).isAssignableFrom(wrapper(actualTypes[i])))       continue;
      return false;
    }
    return true;
  }
 else {
    return false;
  }
}
