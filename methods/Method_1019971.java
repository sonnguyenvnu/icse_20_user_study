/** 
 * Execute <code>supplier</code>, returning a success <code>A</code> or a failure of the thrown  {@link Throwable}.
 * @param supplier the supplier
 * @param < A >      the possible success type
 * @return a new {@link Try} around either a successful A result or the thrown {@link Throwable}
 */
public static <A>Try<A> trying(Fn0<? extends A> supplier){
  try {
    return success(supplier.apply());
  }
 catch (  Throwable t) {
    return failure(t);
  }
}
