/** 
 * Attempt to execute the  {@link SideEffect}, returning  {@link Unit} in a right value. If the runnable throwsan exception, apply <code>leftFn</code> to it, wrap it in a left value, and return it.
 * @param < L >        the left parameter type
 * @param sideEffect the runnable
 * @param leftFn     a function mapping E to L
 * @return {@link Unit} as a right value, or leftFn's mapping result as a left value
 */
public static <L>Either<L,Unit> trying(SideEffect sideEffect,Fn1<? super Throwable,? extends L> leftFn){
  return Try.trying(sideEffect).toEither(leftFn);
}
