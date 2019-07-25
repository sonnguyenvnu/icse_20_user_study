public boolean train(InstanceList ilist,InstanceList validation,InstanceList testing,TransducerEvaluator eval){
  assert (ilist.size() > 0);
  if (emissionEstimator == null) {
    emissionEstimator=new Multinomial.LaplaceEstimator[numStates()];
    transitionEstimator=new Multinomial.LaplaceEstimator[numStates()];
    emissionMultinomial=new Multinomial[numStates()];
    transitionMultinomial=new Multinomial[numStates()];
    Alphabet transitionAlphabet=new Alphabet();
    for (int i=0; i < numStates(); i++)     transitionAlphabet.lookupIndex(((State)states.get(i)).getName(),true);
    for (int i=0; i < numStates(); i++) {
      emissionEstimator[i]=new Multinomial.LaplaceEstimator(inputAlphabet);
      transitionEstimator[i]=new Multinomial.LaplaceEstimator(transitionAlphabet);
      emissionMultinomial[i]=new Multinomial(getUniformArray(inputAlphabet.size()),inputAlphabet);
      transitionMultinomial[i]=new Multinomial(getUniformArray(transitionAlphabet.size()),transitionAlphabet);
    }
    initialEstimator=new Multinomial.LaplaceEstimator(transitionAlphabet);
  }
  for (  Instance instance : ilist) {
    FeatureSequence input=(FeatureSequence)instance.getData();
    FeatureSequence output=(FeatureSequence)instance.getTarget();
    new SumLatticeDefault(this,input,output,new Incrementor());
  }
  initialMultinomial=initialEstimator.estimate();
  for (int i=0; i < numStates(); i++) {
    emissionMultinomial[i]=emissionEstimator[i].estimate();
    transitionMultinomial[i]=transitionEstimator[i].estimate();
    getState(i).setInitialWeight(initialMultinomial.logProbability(getState(i).getName()));
  }
  return true;
}
