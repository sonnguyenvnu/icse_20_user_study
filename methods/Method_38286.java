/** 
 * Resolves object to a class.
 */
protected static Class resolveClass(final Object object){
  Class type=object.getClass();
  return type == Class.class ? (Class)object : type;
}
