/** 
 * Flip this  {@link Iso} around.
 * @return the mirrored {@link Iso}
 */
default Iso<B,A,T,S> mirror(){
  return unIso().into((sa,bt) -> iso(bt,sa));
}
