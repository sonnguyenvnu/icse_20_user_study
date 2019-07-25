/** 
 * {@inheritDoc}
 */
@Override public final Choice2<A,B> converge(Fn1<? super C,? extends CoProduct2<A,B,?>> convergenceFn){
  return match(Choice2::a,Choice2::b,convergenceFn.fmap(cp2 -> cp2.match(Choice2::a,Choice2::b)));
}
