/** 
 * {@inheritDoc}
 */
@Override @SuppressWarnings("unchecked") protected List<Object> run() throws Exception {
  Object[] args=toArgs(getCollapsedRequests());
  return (List)process(args);
}
