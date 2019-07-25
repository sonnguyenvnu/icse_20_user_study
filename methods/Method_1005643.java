/** 
 * Makes an instance for the given value. This may (but does not necessarily) return an already-allocated instance.
 * @param type the type of this handle
 * @param ref {@code non-null;} the referenced field or method constant
 * @return {@code non-null;} the appropriate instance
 */
public static CstMethodHandle make(int type,Constant ref){
  if (isAccessor(type)) {
    if (!(ref instanceof CstFieldRef)) {
      throw new IllegalArgumentException("ref has wrong type: " + ref.getClass());
    }
  }
 else   if (isInvocation(type)) {
    if (!(ref instanceof CstBaseMethodRef)) {
      throw new IllegalArgumentException("ref has wrong type: " + ref.getClass());
    }
  }
 else {
    throw new IllegalArgumentException("type is out of range: " + type);
  }
  return new CstMethodHandle(type,ref);
}
