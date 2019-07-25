/** 
 * Wraps a  {@link DeleteRequest} in a {@link RequestWrapper}
 */
public static RequestWrapper<DeleteRequest> wrap(DeleteRequest request){
  return new DeleteRequestWrapper(request);
}
