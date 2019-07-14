public static void sortBeam(ArrayList<Configuration> beam,TreeSet<BeamElement> beamPreserver,Boolean isNonProjective,Instance instance,int beamWidth,boolean rootFirst,int featureLength,AveragedPerceptron classifier,Collection<Integer> dependencyRelations){
  for (int b=0; b < beam.size(); b++) {
    Configuration configuration=beam.get(b);
    State currentState=configuration.state;
    float prevScore=configuration.score;
    boolean canShift=ArcEager.canDo(Action.Shift,currentState);
    boolean canReduce=ArcEager.canDo(Action.Reduce,currentState);
    boolean canRightArc=ArcEager.canDo(Action.RightArc,currentState);
    boolean canLeftArc=ArcEager.canDo(Action.LeftArc,currentState);
    Object[] features=FeatureExtractor.extractAllParseFeatures(configuration,featureLength);
    if (!canShift && !canReduce && !canRightArc && !canLeftArc) {
      beamPreserver.add(new BeamElement(prevScore,b,4,-1));
      if (beamPreserver.size() > beamWidth)       beamPreserver.pollFirst();
    }
    if (canShift) {
      float score=classifier.shiftScore(features,true);
      float addedScore=score + prevScore;
      beamPreserver.add(new BeamElement(addedScore,b,0,-1));
      if (beamPreserver.size() > beamWidth)       beamPreserver.pollFirst();
    }
    if (canReduce) {
      float score=classifier.reduceScore(features,true);
      float addedScore=score + prevScore;
      beamPreserver.add(new BeamElement(addedScore,b,1,-1));
      if (beamPreserver.size() > beamWidth)       beamPreserver.pollFirst();
    }
    if (canRightArc) {
      float[] rightArcScores=classifier.rightArcScores(features,true);
      for (      int dependency : dependencyRelations) {
        float score=rightArcScores[dependency];
        float addedScore=score + prevScore;
        beamPreserver.add(new BeamElement(addedScore,b,2,dependency));
        if (beamPreserver.size() > beamWidth)         beamPreserver.pollFirst();
      }
    }
    if (canLeftArc) {
      float[] leftArcScores=classifier.leftArcScores(features,true);
      for (      int dependency : dependencyRelations) {
        float score=leftArcScores[dependency];
        float addedScore=score + prevScore;
        beamPreserver.add(new BeamElement(addedScore,b,3,dependency));
        if (beamPreserver.size() > beamWidth)         beamPreserver.pollFirst();
      }
    }
  }
}
