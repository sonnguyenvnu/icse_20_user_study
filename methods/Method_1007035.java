/** 
 * Returns an Effect that waits for a given Future to obtain a value, discarding the value.
 * @return An effect, which, given a Future, waits for it to obtain a value, discarding the value.
 */
public static <A>Effect1<Future<A>> discard(){
  return a -> Strategy.<A>obtain().f(a)._1();
}
