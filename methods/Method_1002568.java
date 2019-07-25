/** 
 * Return a unstructured data response. Before returning from this method, the data content must be fully written to the {@link UnstructuredDataWriter#getOutputStream()} or it could result in incomplete or empty response.
 * @param writer The response writer
 */
default void get(@UnstructuredDataWriterParam UnstructuredDataWriter writer){
  throw new RoutingException("'get' is not implemented",400);
}
