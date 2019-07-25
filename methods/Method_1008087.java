/** 
 * Wraps a  {@link IndexRequest} in a {@link RequestWrapper}
 */
public static RequestWrapper<IndexRequest> wrap(IndexRequest request){
  return new IndexRequestWrapper(request);
}
