/** 
 * Returns a closure that will do nothing.
 * @param < T > The closure argument type.
 * @return A closure that does nothing.
 */
@SuppressWarnings("unchecked") public static <T>Closure<T> noop(){
  return (Closure<T>)NOOP;
}
