public static void addAvailableBeamElements(ArrayList<BeamElement> elements,float prevScore,boolean canShift,boolean canReduce,boolean canRightArc,boolean canLeftArc,Object[] features,AveragedPerceptron classifier,boolean isDecode,int b,ArrayList<Integer> dependencyRelations){
  if (canShift) {
    float score=classifier.shiftScore(features,isDecode);
    float addedScore=score + prevScore;
    elements.add(new BeamElement(addedScore,b,0,-1));
  }
  if (canReduce) {
    float score=classifier.reduceScore(features,isDecode);
    float addedScore=score + prevScore;
    elements.add(new BeamElement(addedScore,b,1,-1));
  }
  if (canRightArc) {
    float[] rightArcScores=classifier.rightArcScores(features,isDecode);
    for (    int dependency : dependencyRelations) {
      float score=rightArcScores[dependency];
      float addedScore=score + prevScore;
      elements.add(new BeamElement(addedScore,b,2,dependency));
    }
  }
  if (canLeftArc) {
    float[] leftArcScores=classifier.leftArcScores(features,isDecode);
    for (    int dependency : dependencyRelations) {
      float score=leftArcScores[dependency];
      float addedScore=score + prevScore;
      elements.add(new BeamElement(addedScore,b,3,dependency));
    }
  }
}
