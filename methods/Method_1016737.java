/** 
 * Create a Random classifier from a set of training data.
 * @param trainingList The InstanceList to be used to train the classifier.
 * @return The Random classifier as trained on the trainingList
 */
@Override public RandomClassifier train(InstanceList trainingList){
  if (trainingList != null) {
    if (this.instancePipe == null)     this.instancePipe=trainingList.getPipe();
 else     if (this.instancePipe != trainingList.getPipe())     throw new IllegalArgumentException("Training set pipe does not match that of NaiveBayesTrainer.");
    this.dataAlphabet=this.instancePipe.getDataAlphabet();
    this.targetAlphabet=this.instancePipe.getTargetAlphabet();
  }
  this.classifier=new RandomClassifier(this.instancePipe);
  for (  Instance instance : trainingList) {
    if (dataAlphabet == null) {
      this.dataAlphabet=instance.getDataAlphabet();
      this.targetAlphabet=instance.getTargetAlphabet();
    }
 else     if (!Alphabet.alphabetsMatch(instance,this))     throw new IllegalArgumentException("Training set alphabets do not match those of NaiveBayesTrainer.");
    Label label=(Label)instance.getTarget();
    this.classifier.addTargetLabel(label);
  }
  return this.classifier;
}
