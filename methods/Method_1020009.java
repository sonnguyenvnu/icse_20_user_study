/** 
 * Choose between a successful result <code>b</code> or returning back the input, <code>a</code>.
 * @return an {@link Fn1} that chooses between its input (in case of failure) or its output.
 */
@Override default Fn1<A,Choice2<A,B>> choose(){
  return a -> Either.trying(() -> apply(a),constantly(a)).match(Choice2::a,Choice2::b);
}
