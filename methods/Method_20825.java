/** 
 * Returns the first non-`null` value of its arguments.
 */
@NonNull public static <T>T coalesce(final @Nullable T value,final @NonNull T theDefault){
  if (value != null) {
    return value;
  }
  return theDefault;
}
