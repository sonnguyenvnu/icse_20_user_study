/** 
 * Returns an action that does precisely nothing.
 * @return an action that does precisely nothing
 */
static <T>Action<T> noop(){
  return thing -> {
  }
;
}
