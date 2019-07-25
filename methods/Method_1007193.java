/** 
 * @deprecated since 4.7. Use {@link P#weakMemo(F0)} instead.
 */
@Deprecated public static <A>P1<A> memo(F<Unit,A> f){
  return P.weakMemo(() -> f.f(unit()));
}
