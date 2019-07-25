/** 
 * Create an  {@link Effect} that accepts an input and does nothing;
 * @param < A > any desired input type
 * @return the noop {@link Effect}
 */
@SuppressWarnings("unused") static <A>Effect<A> noop(){
  return effect(NOOP);
}
