/** 
 * Diverge this coproduct by introducing another possible type that it could represent.
 * @param < E > the additional possible type of this coproduct
 * @return a Coproduct5&lt;A, B, C, D, E&gt;
 * @see CoProduct2#diverge()
 */
default <E>CoProduct5<A,B,C,D,E,? extends CoProduct5<A,B,C,D,E,?>> diverge(){
  return new CoProduct5<A,B,C,D,E,CoProduct5<A,B,C,D,E,?>>(){
    @Override public <R>R match(    Fn1<? super A,? extends R> aFn,    Fn1<? super B,? extends R> bFn,    Fn1<? super C,? extends R> cFn,    Fn1<? super D,? extends R> dFn,    Fn1<? super E,? extends R> eFn){
      return CoProduct4.this.match(aFn,bFn,cFn,dFn);
    }
  }
;
}
