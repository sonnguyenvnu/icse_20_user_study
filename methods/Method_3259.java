private POSInstance createInstance(String[] words){
  final FeatureTemplate[] featureTemplateArray=model.getFeatureTemplateArray();
  final String[][] table=new String[words.length][5];
  for (int i=0; i < words.length; i++) {
    extractFeature(words[i],table[i]);
  }
  return new POSInstance(words,model.featureMap){
    @Override protected int[] extractFeature(    String[] words,    FeatureMap featureMap,    int position){
      StringBuilder sbFeature=new StringBuilder();
      List<Integer> featureVec=new LinkedList<Integer>();
      for (int i=0; i < featureTemplateArray.length; i++) {
        Iterator<int[]> offsetIterator=featureTemplateArray[i].offsetList.iterator();
        Iterator<String> delimiterIterator=featureTemplateArray[i].delimiterList.iterator();
        delimiterIterator.next();
        while (offsetIterator.hasNext()) {
          int[] offset=offsetIterator.next();
          int t=offset[0] + position;
          int j=offset[1];
          if (t < 0)           sbFeature.append(FeatureIndex.BOS[-(t + 1)]);
 else           if (t >= words.length)           sbFeature.append(FeatureIndex.EOS[t - words.length]);
 else           sbFeature.append(table[t][j]);
          if (delimiterIterator.hasNext())           sbFeature.append(delimiterIterator.next());
 else           sbFeature.append(i);
        }
        addFeatureThenClear(sbFeature,featureVec,featureMap);
      }
      return toFeatureArray(featureVec);
    }
  }
;
}
