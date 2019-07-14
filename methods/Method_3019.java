public ArrayList<BeamElement> call() throws Exception {
  ArrayList<BeamElement> elements=new ArrayList<BeamElement>(dependencyRelations.size() * 2 + 3);
  boolean isNonProjective=false;
  if (instance.isNonprojective()) {
    isNonProjective=true;
  }
  State currentState=configuration.state;
  float prevScore=configuration.score;
  boolean canShift=ArcEager.canDo(Action.Shift,currentState);
  boolean canReduce=ArcEager.canDo(Action.Reduce,currentState);
  boolean canRightArc=ArcEager.canDo(Action.RightArc,currentState);
  boolean canLeftArc=ArcEager.canDo(Action.LeftArc,currentState);
  Object[] features=FeatureExtractor.extractAllParseFeatures(configuration,featureLength);
  if (canShift) {
    if (isNonProjective || instance.actionCost(Action.Shift,-1,currentState) == 0) {
      float score=classifier.shiftScore(features,isDecode);
      float addedScore=score + prevScore;
      elements.add(new BeamElement(addedScore,b,0,-1));
    }
  }
  if (canReduce) {
    if (isNonProjective || instance.actionCost(Action.Reduce,-1,currentState) == 0) {
      float score=classifier.reduceScore(features,isDecode);
      float addedScore=score + prevScore;
      elements.add(new BeamElement(addedScore,b,1,-1));
    }
  }
  if (canRightArc) {
    float[] rightArcScores=classifier.rightArcScores(features,isDecode);
    for (    int dependency : dependencyRelations) {
      if (isNonProjective || instance.actionCost(Action.RightArc,dependency,currentState) == 0) {
        float score=rightArcScores[dependency];
        float addedScore=score + prevScore;
        elements.add(new BeamElement(addedScore,b,2,dependency));
      }
    }
  }
  if (canLeftArc) {
    float[] leftArcScores=classifier.leftArcScores(features,isDecode);
    for (    int dependency : dependencyRelations) {
      if (isNonProjective || instance.actionCost(Action.LeftArc,dependency,currentState) == 0) {
        float score=leftArcScores[dependency];
        float addedScore=score + prevScore;
        elements.add(new BeamElement(addedScore,b,3,dependency));
      }
    }
  }
  if (elements.size() == 0) {
    addAvailableBeamElements(elements,prevScore,canShift,canReduce,canRightArc,canLeftArc,features,classifier,isDecode,b,dependencyRelations);
  }
  return elements;
}
