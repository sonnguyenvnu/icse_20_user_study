public void estimate(){
  if (initialStateCounts == null || finalStateCounts == null)   throw new IllegalStateException("This transducer not currently trainable.");
  Multinomial initialStateDistribution=initialStateCounts.estimate();
  Multinomial finalStateDistribution=finalStateCounts.estimate();
  for (int i=0; i < states.size(); i++) {
    State s=states.get(i);
    s.initialWeight=initialStateDistribution.logProbability(i);
    s.finalWeight=finalStateDistribution.logProbability(i);
    s.estimate();
  }
}
