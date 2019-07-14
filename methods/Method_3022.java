/** 
 * ??beam?????????????
 * @param beam
 * @param beamPreserver
 * @throws Exception
 */
private void beamSortOneThread(ArrayList<Configuration> beam,TreeSet<BeamElement> beamPreserver){
  for (int b=0; b < beam.size(); b++) {
    Configuration configuration=beam.get(b);
    State currentState=configuration.state;
    float prevScore=configuration.score;
    boolean canShift=ArcEager.canDo(Action.Shift,currentState);
    boolean canReduce=ArcEager.canDo(Action.Reduce,currentState);
    boolean canRightArc=ArcEager.canDo(Action.RightArc,currentState);
    boolean canLeftArc=ArcEager.canDo(Action.LeftArc,currentState);
    Object[] features=FeatureExtractor.extractAllParseFeatures(configuration,featureLength);
    if (canShift) {
      float score=classifier.shiftScore(features,false);
      float addedScore=score + prevScore;
      addToBeam(beamPreserver,b,addedScore,0,-1,options.beamWidth);
    }
    if (canReduce) {
      float score=classifier.reduceScore(features,false);
      float addedScore=score + prevScore;
      addToBeam(beamPreserver,b,addedScore,1,-1,options.beamWidth);
    }
    if (canRightArc) {
      float[] rightArcScores=classifier.rightArcScores(features,false);
      for (      int dependency : dependencyRelations) {
        float score=rightArcScores[dependency];
        float addedScore=score + prevScore;
        addToBeam(beamPreserver,b,addedScore,2,dependency,options.beamWidth);
      }
    }
    if (canLeftArc) {
      float[] leftArcScores=classifier.leftArcScores(features,false);
      for (      int dependency : dependencyRelations) {
        float score=leftArcScores[dependency];
        float addedScore=score + prevScore;
        addToBeam(beamPreserver,b,addedScore,3,dependency,options.beamWidth);
      }
    }
  }
}
