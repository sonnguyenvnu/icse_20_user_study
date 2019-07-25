/** 
 * Add multiple terms at the next position in the phrase.  Any of the terms may match.
 * @see org.apache.lucene.search.PhraseQuery.Builder#add(Term)
 */
public void add(Term[] terms){
  int position=0;
  if (positions.size() > 0)   position=positions.get(positions.size() - 1) + 1;
  add(terms,position);
}
