/** 
 * Cast a `null`able value into a non-`null` value, and throw a `NullPointerException` if the value is `null`. Provide a message for a better description of why you require this value to be non-`null`.
 */
public static @NonNull <T>T requireNonNull(final @Nullable T value,final @NonNull String message) throws NullPointerException {
  if (value == null) {
    throw new NullPointerException(message);
  }
  return value;
}
