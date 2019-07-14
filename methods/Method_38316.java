/** 
 * {@inheritDoc}
 */
@Override public String generateQuery(){
  reset();
  SqlChunk chunk=firstChunk;
  while (chunk != null) {
    chunk.init(this);
    chunk=chunk.getNextChunk();
  }
  StringBuilder query=new StringBuilder();
  chunk=firstChunk;
  try {
    while (chunk != null) {
      chunk.process(query);
      chunk=chunk.getNextChunk();
    }
  }
 catch (  DbSqlBuilderException dsbex) {
    dsbex.setQueryString(query.toString());
    throw dsbex;
  }
  generatedQuery=query.toString();
  return generatedQuery;
}
