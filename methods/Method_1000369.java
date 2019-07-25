protected int _exec(final DaoStatement... sts){
  if (sts != null)   for (  DaoStatement ds : sts) {
    ds.setExpert(expert);
  }
  final DaoInterceptorChain callback=new DaoInterceptorChain(sts);
  callback.setExecutor(executor);
  callback.setAutoTransLevel(autoTransLevel);
  callback.setInterceptors(Collections.unmodifiableList(this._interceptors));
  run(callback);
  return callback.getUpdateCount();
}
