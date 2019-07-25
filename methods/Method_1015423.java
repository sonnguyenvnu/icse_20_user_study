public T execute(Buffer data,boolean block_for_results) throws Exception {
  if (corr == null)   return null;
  sendRequest(data);
  if (!block_for_results || options.mode() == ResponseMode.GET_NONE)   return null;
  long timeout=options.timeout();
  return timeout > 0 ? waitForCompletion(options.timeout(),TimeUnit.MILLISECONDS) : waitForCompletion();
}
