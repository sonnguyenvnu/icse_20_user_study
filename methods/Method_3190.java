/** 
 * Apply IDF(inverse document frequency) weighting.
 * @param df    document frequencies
 * @param ndocs the number of documents
 */
void idf(HashMap<Integer,Integer> df,int ndocs){
  for (  Map.Entry<Integer,Double> entry : feature_.entrySet()) {
    Integer denom=df.get(entry.getKey());
    if (denom == null)     denom=1;
    entry.setValue((double)(entry.getValue() * Math.log(ndocs / denom)));
  }
}
