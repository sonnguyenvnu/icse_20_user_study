/** 
 * Creates a right either instance.
 * @param value The right value to wrap - may not be null.
 * @param < L > The left type.
 * @param < R > The right type.
 * @return A right either instance wrapping {@code value}.
 */
public static <L,R>Either<L,R> right(R value){
  return new Either<L,R>(Optional.<L>absent(),Optional.of(value));
}
