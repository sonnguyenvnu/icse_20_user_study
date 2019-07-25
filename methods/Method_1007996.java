/** 
 * Index a document using the Index API See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-index_.html">Index API on elastic.co</a>
 */
public final IndexResponse index(IndexRequest indexRequest,Header... headers) throws IOException {
  return performRequestAndParseEntity(indexRequest,Request::index,IndexResponse::fromXContent,emptySet(),headers);
}
