private void updateWeights(Configuration initialConfiguration,float maxViol,boolean isPartial,Configuration bestScoringOracle,Pair<Configuration,Configuration> maxViolPair,ArrayList<Configuration> beam){
  Configuration predicted;
  Configuration finalOracle;
  if (!updateMode.equals("max_violation")) {
    finalOracle=bestScoringOracle;
    predicted=beam.get(0);
  }
 else {
    float violation=beam.get(0).getScore(true) - bestScoringOracle.getScore(true);
    if (violation > maxViol) {
      maxViolPair=new Pair<Configuration,Configuration>(beam.get(0),bestScoringOracle);
    }
    predicted=maxViolPair.first;
    finalOracle=maxViolPair.second;
  }
  Object[] predictedFeatures=new Object[featureLength];
  Object[] oracleFeatures=new Object[featureLength];
  for (int f=0; f < predictedFeatures.length; f++) {
    oracleFeatures[f]=new HashMap<Pair<Integer,Long>,Float>();
    predictedFeatures[f]=new HashMap<Pair<Integer,Long>,Float>();
  }
  Configuration predictedConfiguration=initialConfiguration.clone();
  Configuration oracleConfiguration=initialConfiguration.clone();
  for (  int action : finalOracle.actionHistory) {
    boolean isTrueFeature=isTrueFeature(isPartial,oracleConfiguration,action);
    if (isTrueFeature) {
      Object[] feats=FeatureExtractor.extractAllParseFeatures(oracleConfiguration,featureLength);
      for (int f=0; f < feats.length; f++) {
        Pair<Integer,Object> featName=new Pair<Integer,Object>(action,feats[f]);
        HashMap<Pair<Integer,Object>,Float> map=(HashMap<Pair<Integer,Object>,Float>)oracleFeatures[f];
        Float value=map.get(featName);
        if (value == null)         map.put(featName,1.0f);
 else         map.put(featName,value + 1);
      }
    }
    if (action == 0) {
      ArcEager.shift(oracleConfiguration.state);
    }
 else     if (action == 1) {
      ArcEager.reduce(oracleConfiguration.state);
    }
 else     if (action >= (3 + dependencyRelations.size())) {
      int dependency=action - (3 + dependencyRelations.size());
      ArcEager.leftArc(oracleConfiguration.state,dependency);
    }
 else     if (action >= 3) {
      int dependency=action - 3;
      ArcEager.rightArc(oracleConfiguration.state,dependency);
    }
  }
  for (  int action : predicted.actionHistory) {
    boolean isTrueFeature=isTrueFeature(isPartial,predictedConfiguration,action);
    if (isTrueFeature) {
      Object[] feats=FeatureExtractor.extractAllParseFeatures(predictedConfiguration,featureLength);
      if (action != 2)       for (int f=0; f < feats.length; f++) {
        Pair<Integer,Object> featName=new Pair<Integer,Object>(action,feats[f]);
        HashMap<Pair<Integer,Object>,Float> map=(HashMap<Pair<Integer,Object>,Float>)predictedFeatures[f];
        Float value=map.get(featName);
        if (value == null)         map.put(featName,1.f);
 else         map.put(featName,map.get(featName) + 1);
      }
    }
    State state=predictedConfiguration.state;
    if (action == 0) {
      ArcEager.shift(state);
    }
 else     if (action == 1) {
      ArcEager.reduce(state);
    }
 else     if (action >= 3 + dependencyRelations.size()) {
      int dependency=action - (3 + dependencyRelations.size());
      ArcEager.leftArc(state,dependency);
    }
 else     if (action >= 3) {
      int dependency=action - 3;
      ArcEager.rightArc(state,dependency);
    }
 else     if (action == 2) {
      ArcEager.unShift(state);
    }
  }
  for (int f=0; f < predictedFeatures.length; f++) {
    HashMap<Pair<Integer,Object>,Float> map=(HashMap<Pair<Integer,Object>,Float>)predictedFeatures[f];
    HashMap<Pair<Integer,Object>,Float> map2=(HashMap<Pair<Integer,Object>,Float>)oracleFeatures[f];
    for (    Pair<Integer,Object> feat : map.keySet()) {
      int action=feat.first;
      LabeledAction labeledAction=new LabeledAction(action,dependencyRelations.size());
      Action actionType=labeledAction.action;
      int dependency=labeledAction.label;
      if (feat.second != null) {
        Object feature=feat.second;
        if (!(map2.containsKey(feat) && map2.get(feat).equals(map.get(feat))))         classifier.changeWeight(actionType,f,feature,dependency,-map.get(feat));
      }
    }
    for (    Pair<Integer,Object> feat : map2.keySet()) {
      int action=feat.first;
      LabeledAction labeledAction=new LabeledAction(action,dependencyRelations.size());
      Action actionType=labeledAction.action;
      int dependency=labeledAction.label;
      if (feat.second != null) {
        Object feature=feat.second;
        if (!(map.containsKey(feat) && map.get(feat).equals(map2.get(feat))))         classifier.changeWeight(actionType,f,feature,dependency,map2.get(feat));
      }
    }
  }
}
