/** 
 * Returns an equivalence that delegates to  {@link Object#equals} and {@link Object#hashCode}. {@link Equivalence#equivalent} returns {@code true} if both values are null, or if neithervalue is null and  {@link Object#equals} returns {@code true}.  {@link Equivalence#hash} returns{@code 0} if passed a null value.
 * @since 13.0
 * @since 8.0 (in Equivalences with null-friendly behavior)
 * @since 4.0 (in Equivalences)
 */
@Nonnull public static Equivalence<Object> equals(){
  return Equals.INSTANCE;
}
