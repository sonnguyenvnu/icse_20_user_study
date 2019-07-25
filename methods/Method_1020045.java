/** 
 * Produce an  {@link IO} that throws the given {@link Throwable} when executed.
 * @param t   the {@link Throwable}
 * @param < A > any result type
 * @return the {@link IO}
 */
public static <A>IO<A> throwing(Throwable t){
  return io(() -> {
    throw t;
  }
);
}
