public static ResponseHandler seq(final ResponseHandler handler,final ResponseHandler... handlers){
  checkNotNull(handler,"Sequence handler should not be null");
  checkArgument(handlers.length > 0,"Sequence handlers should not be null");
  return newSeq(asIterable(handler,handlers));
}
