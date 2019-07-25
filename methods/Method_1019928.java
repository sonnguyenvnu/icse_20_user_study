/** 
 * Diverge this coproduct by introducing another possible type that it could represent.
 * @param < F > the additional possible type of this coproduct
 * @return a Coproduct6&lt;A, B, C, D, E, F&gt;
 * @see CoProduct2#diverge()
 */
default <F>CoProduct6<A,B,C,D,E,F,? extends CoProduct6<A,B,C,D,E,F,?>> diverge(){
  return new CoProduct6<A,B,C,D,E,F,CoProduct6<A,B,C,D,E,F,?>>(){
    @Override public <R>R match(    Fn1<? super A,? extends R> aFn,    Fn1<? super B,? extends R> bFn,    Fn1<? super C,? extends R> cFn,    Fn1<? super D,? extends R> dFn,    Fn1<? super E,? extends R> eFn,    Fn1<? super F,? extends R> fFn){
      return CoProduct5.this.match(aFn,bFn,cFn,dFn,eFn);
    }
  }
;
}
