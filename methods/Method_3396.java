protected int[] extractFeature(String[] words,FeatureMap featureMap,int position){
  List<Integer> featVec=new ArrayList<Integer>();
  String preWord=position >= 1 ? words[position - 1] : "_B_";
  String curWord=words[position];
  String nextWord=position <= words.length - 2 ? words[position + 1] : "_E_";
  StringBuilder sbFeature=new StringBuilder();
  sbFeature.append(preWord).append('1');
  addFeatureThenClear(sbFeature,featVec,featureMap);
  sbFeature.append(curWord).append('2');
  addFeatureThenClear(sbFeature,featVec,featureMap);
  sbFeature.append(nextWord).append('3');
  addFeatureThenClear(sbFeature,featVec,featureMap);
  int length=curWord.length();
  sbFeature.append(curWord.substring(0,1)).append('4');
  addFeatureThenClear(sbFeature,featVec,featureMap);
  if (length > 1) {
    sbFeature.append(curWord.substring(0,2)).append('4');
    addFeatureThenClear(sbFeature,featVec,featureMap);
  }
  if (length > 2) {
    sbFeature.append(curWord.substring(0,3)).append('4');
    addFeatureThenClear(sbFeature,featVec,featureMap);
  }
  sbFeature.append(curWord.charAt(length - 1)).append('5');
  addFeatureThenClear(sbFeature,featVec,featureMap);
  if (length > 1) {
    sbFeature.append(curWord.substring(length - 2)).append('5');
    addFeatureThenClear(sbFeature,featVec,featureMap);
  }
  if (length > 2) {
    sbFeature.append(curWord.substring(length - 3)).append('5');
    addFeatureThenClear(sbFeature,featVec,featureMap);
  }
  return toFeatureArray(featVec);
}
