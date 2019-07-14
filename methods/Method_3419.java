@Override protected List<Integer> extractFeature(String text,FeatureMap featureMap){
  List<Integer> featureList=new LinkedList<Integer>();
  String givenName=extractGivenName(text);
  addFeature("1" + givenName.substring(0,1),featureMap,featureList);
  addFeature("2" + givenName.substring(1),featureMap,featureList);
  return featureList;
}
