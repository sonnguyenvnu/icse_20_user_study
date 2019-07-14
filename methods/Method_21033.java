/** 
 * Tries to extract an  {@link ErrorEnvelope} from an exception, and if itcan't returns null.
 */
public static @Nullable ErrorEnvelope fromThrowable(final @NonNull Throwable t){
  if (t instanceof ApiException) {
    final ApiException exception=(ApiException)t;
    return exception.errorEnvelope();
  }
  return null;
}
