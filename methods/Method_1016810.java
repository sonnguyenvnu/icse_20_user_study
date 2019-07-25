public Instance pipe(Instance carrier){
  Classification classification=(Classification)carrier.getData();
  PropertyList features=null;
  LabelVector lv=classification.getLabelVector();
  Label bestLabel=lv.getBestLabel();
  Instance inst=(Instance)classification.getInstance();
  FeatureVector fv=(FeatureVector)inst.getData();
  Alphabet fdict=fv.getAlphabet();
  double winningThreshold=.990;
  double varianceThreshold=.15;
  double secondThreshold=.03;
  double winningScore=lv.getValueAtRank(0);
  double marginOfVictory=winningScore - lv.getValueAtRank(1);
  features=PropertyList.add("winningScore",winningScore,features);
  features=PropertyList.add("secondScore",lv.getValueAtRank(1),features);
  for (int i=0; i < lv.numLocations(); i++) {
    features=PropertyList.add(lv.getLabelAtRank(i).toString() + "HasValue",lv.valueAtLocation(i),features);
  }
  features=PropertyList.add("MarginOfVictory",marginOfVictory,features);
  features=PropertyList.add("numFeatures",((double)fv.numLocations() / fdict.size()),features);
  features=PropertyList.add(bestLabel.toString() + "IsFirst-" + lv.getLabelAtRank(1).toString() + "IsSecond",1.0,features);
  features=PropertyList.add("Range",winningScore - lv.getValueAtRank(lv.numLocations() - 1),features);
  features=PropertyList.add(bestLabel.toString() + "IsFirst",1.0,features);
  features=PropertyList.add(lv.getLabelAtRank(1).toString() + "IsSecond",1.0,features);
  carrier.setTarget(((LabelAlphabet)getTargetAlphabet()).lookupLabel(classification.bestLabelIsCorrect() ? "correct" : "incorrect"));
  carrier.setData(new FeatureVector((Alphabet)getDataAlphabet(),features,false));
  carrier.setName(inst.getName());
  carrier.setSource(inst.getSource());
  return carrier;
}
