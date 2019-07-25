/** 
 * Return a query that will return docs like the passed Fields.
 * @return a query that will return docs like the passed Fields.
 */
public Query like(Fields... likeFields) throws IOException {
  Set<String> fieldNames=new HashSet<>();
  for (  Fields fields : likeFields) {
    for (    String fieldName : fields) {
      fieldNames.add(fieldName);
    }
  }
  BooleanQuery.Builder bq=new BooleanQuery.Builder();
  for (  String fieldName : fieldNames) {
    Map<String,Int> termFreqMap=new HashMap<>();
    for (    Fields fields : likeFields) {
      Terms vector=fields.terms(fieldName);
      if (vector != null) {
        addTermFrequencies(termFreqMap,vector,fieldName);
      }
    }
    addToQuery(createQueue(termFreqMap,fieldName),bq);
  }
  return bq.build();
}
