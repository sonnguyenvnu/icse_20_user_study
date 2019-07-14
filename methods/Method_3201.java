public List<Map.Entry<String,Double>> getKeywordsWithTfIdf(List<Term> termList,int size){
  if (idf == null)   compute();
  Map<String,Double> tfIdf=TfIdf.tfIdf(TfIdf.tf(convert(termList)),idf);
  return topN(tfIdf,size);
}
