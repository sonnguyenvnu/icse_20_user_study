/** 
 * Return a query that will return docs like the passed lucene document ID.
 * @param docNum the documentID of the lucene doc to generate the 'More Like This" query for.
 * @return a query that will return docs like the passed lucene document ID.
 */
public Query like(int docNum) throws IOException {
  if (fieldNames == null) {
    Collection<String> fields=MultiFields.getIndexedFields(ir);
    fieldNames=fields.toArray(new String[fields.size()]);
  }
  return createQuery(retrieveTerms(docNum));
}
