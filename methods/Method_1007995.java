/** 
 * Checks for the existence of a document. Returns true if it exists, false otherwise See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html">Get API on elastic.co</a>
 */
public final boolean exists(GetRequest getRequest,Header... headers) throws IOException {
  return performRequest(getRequest,Request::exists,RestHighLevelClient::convertExistsResponse,emptySet(),headers);
}
