/** 
 * Hack to rethrow unknown Exceptions from  {@link MethodHandle#invokeExact}: 
 */
@SuppressWarnings("unchecked") static <T extends Throwable>void rethrow(Throwable t) throws T {
  throw (T)t;
}
