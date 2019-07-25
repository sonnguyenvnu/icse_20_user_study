/** 
 * Right-to-Left composition of optics. Requires compatibility between <code>A</code> and <code>B</code>.
 * @param g   the other optic
 * @param < R > the new left side of the output profunctor
 * @param < U > the new right side's functor embedding of the output profunctor
 * @return the composed optic
 */
default <R,U>Optic<P,F,R,U,A,B> compose(Optic<? super P,? super F,R,U,S,T> g){
  return new Optic<P,F,R,U,A,B>(){
    @Override public <CoP extends Profunctor<?,?,? extends P>,CoF extends Functor<?,? extends F>,FB extends Functor<B,? extends CoF>,FU extends Functor<U,? extends CoF>,PAFB extends Profunctor<A,FB,? extends CoP>,PRFU extends Profunctor<R,FU,? extends CoP>>PRFU apply(    PAFB pafb){
      return g.<CoP,CoF,Functor<T,? extends CoF>,FU,Profunctor<S,Functor<T,? extends CoF>,? extends CoP>,PRFU>apply(Optic.this.apply(pafb));
    }
  }
;
}
