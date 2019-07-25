/** 
 * Get the cluster info otherwise provided when sending an HTTP request to port 9200
 */
public final MainResponse info(Header... headers) throws IOException {
  return performRequestAndParseEntity(new MainRequest(),(request) -> Request.info(),MainResponse::fromXContent,emptySet(),headers);
}
