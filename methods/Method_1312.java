/** 
 * Returns the first of two given parameters that is not  {@code null}, if either is, or otherwise throws a  {@link NullPointerException}. <p><b>Note:</b> if  {@code first} is represented as an {@link Optional}, this can be accomplished with {@linkplain Optional#or(Object) first.or(second)}. That approach also allows for lazy evaluation of the fallback instance, using  {@linkplain Optional#or(Supplier) first.or(Supplier)}.
 * @return {@code first} if {@code first} is not {@code null}, or {@code second} if {@code first} is {@code null} and {@code second} isnot  {@code null}
 * @throws NullPointerException if both {@code first} and {@code second} were{@code null}
 * @since 3.0
 */
public static <T>T firstNonNull(@Nullable T first,@Nullable T second){
  return first != null ? first : checkNotNull(second);
}
