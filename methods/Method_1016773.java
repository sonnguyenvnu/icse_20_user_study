public void estimate(){
  Alphabet transitionAlphabet=getTransitionAlphabet();
  initialMultinomial=initialEstimator.estimate();
  initialEstimator=new Multinomial.LaplaceEstimator(transitionAlphabet);
  for (int i=0; i < numStates(); i++) {
    State s=(State)getState(i);
    emissionMultinomial[i]=emissionEstimator[i].estimate();
    transitionMultinomial[i]=transitionEstimator[i].estimate();
    s.setInitialWeight(initialMultinomial.logProbability(s.getName()));
    emissionEstimator[i]=new Multinomial.LaplaceEstimator(inputAlphabet);
    transitionEstimator[i]=new Multinomial.LaplaceEstimator(transitionAlphabet);
  }
}
