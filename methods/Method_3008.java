public ArrayList<BeamElement> call(){
  ArrayList<BeamElement> elements=new ArrayList<BeamElement>(dependencyRelations.size() * 2 + 3);
  State currentState=configuration.state;
  float prevScore=configuration.score;
  boolean canShift=ArcEager.canDo(Action.Shift,currentState);
  boolean canReduce=ArcEager.canDo(Action.Reduce,currentState);
  boolean canRightArc=ArcEager.canDo(Action.RightArc,currentState);
  boolean canLeftArc=ArcEager.canDo(Action.LeftArc,currentState);
  Object[] features=FeatureExtractor.extractAllParseFeatures(configuration,featureLength);
  addAvailableBeamElements(elements,prevScore,canShift,canReduce,canRightArc,canLeftArc,features,classifier,isDecode,b,dependencyRelations);
  return elements;
}
