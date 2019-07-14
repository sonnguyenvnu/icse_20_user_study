@Override public boolean isDone(){
  final Object localValue=value;
  return localValue != null & !(localValue instanceof AbstractFuture.SetFuture);
}
