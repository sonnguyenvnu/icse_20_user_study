/** 
 * Throws  {@link NullPointerException} if {@code reference} is null.
 * @param < T > The type of the reference.
 * @param reference The reference.
 * @param errorMessage The exception message to use if the check fails. The message is convertedto a string using  {@link String#valueOf(Object)}.
 * @return The non-null reference that was validated.
 * @throws NullPointerException If {@code reference} is null.
 */
@SuppressWarnings({"contracts.postcondition.not.satisfied","return.type.incompatible"}) @EnsuresNonNull({"#1"}) public static <T>T checkNotNull(@Nullable T reference,Object errorMessage){
  if (ExoPlayerLibraryInfo.ASSERTIONS_ENABLED && reference == null) {
    throw new NullPointerException(String.valueOf(errorMessage));
  }
  return reference;
}
