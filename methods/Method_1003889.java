/** 
 * Creates a left either instance.
 * @param value The left value to wrap - may not be null.
 * @param < L > The left type.
 * @param < R > The right type.
 * @return A left either instance wrapping {@code value}.
 */
public static <L,R>Either<L,R> left(L value){
  return new Either<L,R>(Optional.of(value),Optional.<R>absent());
}
