/** 
 * Given two binary operators over L and R, merge multiple <code>Either&lt;L, R&gt;</code>s into a single <code>Either&lt;L, R&gt;</code>. Note that <code>merge</code> biases towards left values; that is, if any left value exists, the result will be a left value, such that only unanimous right values result in an ultimate right value.
 * @param leftFn  the binary operator for L
 * @param rightFn the binary operator for R
 * @param others  the other Eithers to merge into this one
 * @return the merged Either
 */
@SafeVarargs @SuppressWarnings("varargs") public final Either<L,R> merge(Fn2<? super L,? super L,? extends L> leftFn,Fn2<? super R,? super R,? extends R> rightFn,Either<L,R>... others){
  return foldLeft((x,y) -> x.match(l1 -> y.match(l2 -> left(leftFn.apply(l1,l2)),r -> left(l1)),r1 -> y.match(Either::left,r2 -> right(rightFn.apply(r1,r2)))),this,asList(others));
}
