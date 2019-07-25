/** 
 * Returns a bi-action that does precisely nothing.
 * @return a bi-action that does precisely nothing
 * @param < T > the type of the first thing
 * @param < U > The type of the second thing
 * @since 1.5
 */
static <T,U>BiAction<T,U> noop(){
  return (a,b) -> {
  }
;
}
