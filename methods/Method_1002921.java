@Override public Void apply(@Nullable Void unused,@Nullable Throwable cause){
  if (cancelled) {
    return null;
  }
  if (cause == null) {
    closeDeframer();
  }
 else {
    transportStatusListener.transportReportStatus(GrpcStatus.fromThrowable(cause));
  }
  return null;
}
