void computeAggregation(Map<String,WordInfo> word_cands){
  if (text.length() == 1) {
    aggregation=(float)Math.sqrt(p);
    return;
  }
  for (int i=1; i < text.length(); ++i) {
    aggregation=Math.min(aggregation,p / word_cands.get(text.substring(0,i)).p / word_cands.get(text.substring(i)).p);
  }
}
