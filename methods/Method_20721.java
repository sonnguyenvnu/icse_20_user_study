/** 
 * Prevents an observable from erroring on any  {@link ApiException} exceptions,and any errors that do occur will be piped into the supplied errors publish subject. `null` values will never be sent to the publish subject.
 * @deprecated Use {@link Observable#materialize()} instead.
 */
@Deprecated public static <T>NeverApiErrorTransformer<T> pipeApiErrorsTo(final @NonNull PublishSubject<ErrorEnvelope> errorSubject){
  return new NeverApiErrorTransformer<>(errorSubject::onNext);
}
