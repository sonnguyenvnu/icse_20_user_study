/** 
 * Returns a stream that is either infinite or bounded up to the maximum value of the given iterator starting at the given value and stepping at the given increment.
 * @param e    The enumerator to compute successors from.
 * @param from The value to begin computing successors from.
 * @param step The increment to step.
 * @return A stream that is either infinite or bounded up to the maximum value of the given iterator starting at thegiven value and stepping at the given increment.
 */
public static <A>Stream<A> forever(final Enumerator<A> e,final A from,final long step){
  return cons(from,() -> e.plus(from,step).map(a -> forever(e,a,step)).orSome(Stream.nil()));
}
