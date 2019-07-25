/** 
 * Static factory method for creating a  {@link Prism} given a mapping from<code>S -&gt;  {@link Either}&lt;T, A&gt;</code> and a mapping from <code>B -&gt; T</code>.
 * @param sta the mapping from S -&gt; {@link Either}&lt;T, A&gt;
 * @param bt  the mapping from B -&gt; T
 * @param < S > the input that might fail to map to its output
 * @param < T > the guaranteed output
 * @param < A > the output that might fail to be produced
 * @param < B > the input that guarantees its output
 * @return the {@link Prism}
 */
static <S,T,A,B>Prism<S,T,A,B> prism(Fn1<? super S,? extends CoProduct2<T,A,?>> sta,Fn1<? super B,? extends T> bt){
  return new Prism<S,T,A,B>(){
    @Override public <F extends Applicative<?,F>>Optic<Cocartesian<?,?,?>,F,S,T,A,B> toOptic(    Pure<F> pure){
      return Optic.<Cocartesian<?,?,?>,F,S,T,A,B,Functor<B,? extends F>,Functor<T,? extends F>,Cocartesian<A,Functor<B,? extends F>,?>,Cocartesian<S,Functor<T,? extends F>,?>>optic(pafb -> pafb.<T>cocartesian().diMap(s -> sta.apply(s).match(Choice2::a,Choice2::b),tOrFb -> tOrFb.<Functor<T,? extends F>>match(pure::apply,fb -> fb.fmap(bt))));
    }
  }
;
}
