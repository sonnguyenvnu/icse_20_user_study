@Override public Map<String,Double> predict(Document document){
  AbstractModel model=getModel();
  if (model == null) {
    throw new IllegalStateException("?????????????");
  }
  if (document == null) {
    throw new IllegalArgumentException("?? text == null");
  }
  double[] probs=categorize(document);
  Map<String,Double> scoreMap=new TreeMap<String,Double>();
  for (int i=0; i < probs.length; i++) {
    scoreMap.put(model.catalog[i],probs[i]);
  }
  return scoreMap;
}
