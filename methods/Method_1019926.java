/** 
 * Swap the type parameters.
 * @return The inverted coproduct
 */
default CoProduct2<B,A,? extends CoProduct2<B,A,?>> invert(){
  return new CoProduct2<B,A,CoProduct2<B,A,?>>(){
    @Override public <R>R match(    Fn1<? super B,? extends R> aFn,    Fn1<? super A,? extends R> bFn){
      return CoProduct2.this.match(bFn,aFn);
    }
  }
;
}
