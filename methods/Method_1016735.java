private void setup(InstanceList instances,Instance instance){
  assert (instances != null || instance != null);
  if (instance == null && instances != null)   instance=instances.get(0);
  if (dataAlphabet == null) {
    this.dataAlphabet=instance.getDataAlphabet();
    this.targetAlphabet=instance.getTargetAlphabet();
  }
 else   if (!Alphabet.alphabetsMatch(instance,this))   throw new IllegalArgumentException("Training set alphabets do not match those of NaiveBayesTrainer.");
  if (instances != null) {
    if (instancePipe == null)     instancePipe=instances.getPipe();
 else     if (instancePipe != instances.getPipe())     throw new IllegalArgumentException("Training set pipe does not match that of NaiveBayesTrainer.");
  }
  if (me == null) {
    int numLabels=targetAlphabet.size();
    me=new Multinomial.Estimator[numLabels];
    for (int i=0; i < numLabels; i++) {
      me[i]=(Multinomial.Estimator)featureEstimator.clone();
      me[i].setAlphabet(dataAlphabet);
    }
    pe=(Multinomial.Estimator)priorEstimator.clone();
  }
  if (targetAlphabet.size() > me.length) {
    int targetAlphabetSize=targetAlphabet.size();
    Multinomial.Estimator[] newMe=new Multinomial.Estimator[targetAlphabetSize];
    System.arraycopy(me,0,newMe,0,me.length);
    for (int i=me.length; i < targetAlphabetSize; i++) {
      Multinomial.Estimator mest=(Multinomial.Estimator)featureEstimator.clone();
      mest.setAlphabet(dataAlphabet);
      newMe[i]=mest;
    }
    me=newMe;
  }
}
