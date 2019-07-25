/** 
 * Makes an instance for the given value. This may (but does not necessarily) return an already-allocated instance.
 * @param kind the kind of this handle
 * @param ref the actual referenced constant
 * @return {@code non-null;} the appropriate instance
 */
public static CstMethodHandle make(int kind,Constant ref){
  return new CstMethodHandle(kind,ref);
}
