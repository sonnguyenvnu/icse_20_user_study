/** 
 * Right-to-Left composition of proto-optics. Requires compatibility between <code>A</code> and <code>B</code>.
 * @param g   the other proto-optic
 * @param < R > the new left side of the output profunctor
 * @param < U > the new right side's functor embedding of the output profunctor
 * @return the composed proto-optic
 */
default <R,U>ProtoOptic<P,R,U,A,B> compose(ProtoOptic<? super P,R,U,S,T> g){
  return new ProtoOptic<P,R,U,A,B>(){
    @Override public <F extends Applicative<?,F>>Optic<P,F,R,U,A,B> toOptic(    Pure<F> pure){
      Optic<? super P,F,R,U,S,T> optic=g.toOptic(pure);
      return ProtoOptic.this.toOptic(pure).compose(reframe(optic));
    }
  }
;
}
