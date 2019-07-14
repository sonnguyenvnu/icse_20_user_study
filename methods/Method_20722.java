/** 
 * Prevents an observable from erroring on any  {@link ApiException} exceptions,and any errors that do occur will be piped into the supplied errors actions. `null` values will never be sent to the action.
 * @deprecated Use {@link Observable#materialize()} instead.
 */
@Deprecated public static <T>NeverApiErrorTransformer<T> pipeApiErrorsTo(final @NonNull Action1<ErrorEnvelope> errorAction){
  return new NeverApiErrorTransformer<>(errorAction);
}
