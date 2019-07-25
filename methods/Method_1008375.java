/** 
 * Return a query that will return docs like the passed Terms.
 * @return a query that will return docs like the passed Terms.
 */
public Query like(Terms... likeTerms) throws IOException {
  Map<String,Int> termFreqMap=new HashMap<>();
  for (  Terms vector : likeTerms) {
    addTermFrequencies(termFreqMap,vector);
  }
  return createQuery(createQueue(termFreqMap));
}
