public GEConstraint copy(){
  return new SelfTransitionGEConstraint(selfTransProb,weight,numTokens,expectation);
}
