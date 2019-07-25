/** 
 * Return a query that will return docs like the passed Readers. This was added in order to treat multi-value fields.
 * @return a query that will return docs like the passed Readers.
 */
public Query like(String fieldName,Reader... readers) throws IOException {
  Map<String,Int> words=new HashMap<>();
  for (  Reader r : readers) {
    addTermFrequencies(r,words,fieldName);
  }
  return createQuery(createQueue(words));
}
