/** 
 * Diverge this coproduct by introducing another possible type that it could represent. As no morphisms can be provided mapping current types to the new type, this operation merely acts as a convenience method to allow the use of a more convergent coproduct with a more divergent one; that is, if a <code>CoProduct3&lt;String, Integer, Boolean&gt;</code> is expected, a <code>CoProduct2&lt;String, Integer&gt;</code> should suffice. <p> Generally, we use inheritance to make this a non-issue; however, with coproducts of differing magnitudes, we cannot guarantee variance compatibility in one direction conveniently at construction time, and in the other direction, at all. A  {@link CoProduct2} could not be a {@link CoProduct3} without specifying all type parametersthat are possible for a  {@link CoProduct3} - more specifically, the third possible type - which is notnecessarily known at construction time, or even useful if never used in the context of a  {@link CoProduct3}. The inverse inheritance relationship -  {@link CoProduct3} &lt; {@link CoProduct2} - is inherently unsound, as a{@link CoProduct3} cannot correctly implement {@link CoProduct2#match}, given that the third type <code>C</code> is always possible. <p> For this reason, there is a <code>diverge</code> method supported between all <code>CoProduct</code> types of single magnitude difference.
 * @param < C > the additional possible type of this coproduct
 * @return a {@link CoProduct3}&lt;A, B, C&gt;
 */
default <C>CoProduct3<A,B,C,? extends CoProduct3<A,B,C,?>> diverge(){
  return new CoProduct3<A,B,C,CoProduct3<A,B,C,?>>(){
    @Override public <R>R match(    Fn1<? super A,? extends R> aFn,    Fn1<? super B,? extends R> bFn,    Fn1<? super C,? extends R> cFn){
      return CoProduct2.this.match(aFn,bFn);
    }
  }
;
}
