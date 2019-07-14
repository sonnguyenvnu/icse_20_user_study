/** 
 * Determines if class should be examined for Madvoc annotations. Array, anonymous, primitive, interfaces and so on should be ignored. Sometimes, checking may fail due to e.g. <code>NoClassDefFoundError</code>; we should continue searching anyway.
 */
protected boolean checkClass(final Class clazz){
  try {
    if (clazz.isAnonymousClass()) {
      return false;
    }
    if (clazz.isArray() || clazz.isEnum()) {
      return false;
    }
    if (clazz.isInterface()) {
      return false;
    }
    if (clazz.isLocalClass()) {
      return false;
    }
    if ((clazz.isMemberClass() ^ Modifier.isStatic(clazz.getModifiers()))) {
      return false;
    }
    if (clazz.isPrimitive()) {
      return false;
    }
    int modifiers=clazz.getModifiers();
    if (Modifier.isAbstract(modifiers)) {
      return false;
    }
    return true;
  }
 catch (  Throwable ignore) {
    return false;
  }
}
