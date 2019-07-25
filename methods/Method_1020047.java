/** 
 * Convenience static method equivalent to  {@link Monad#flatMap(Fn1) flatMap}{@link Id#id() (id())};
 * @param mma  the outer monad
 * @param < M >  the monad type
 * @param < A >  the nested type parameter
 * @param < MA > the nested monad
 * @return the nested monad
 */
static <M extends Monad<?,M>,A,MA extends Monad<A,M>>MA join(Monad<? extends MA,M> mma){
  return mma.flatMap(id()).coerce();
}
