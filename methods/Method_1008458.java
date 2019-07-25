/** 
 * Returns an array of all registered pairs of handle IDs and exception classes. These pairs are provided for every registered exception.
 * @return an array of all registered pairs of handle IDs and exception classes
 */
static Tuple<Integer,Class<? extends ElasticsearchException>>[] classes(){
  @SuppressWarnings("unchecked") final Tuple<Integer,Class<? extends ElasticsearchException>>[] ts=Arrays.stream(ElasticsearchExceptionHandle.values()).map(h -> Tuple.tuple(h.id,h.exceptionClass)).toArray(Tuple[]::new);
  return ts;
}
