public ConfidencePredictingClassifier train(InstanceList trainList){
  FeatureSelection selectedFeatures=trainList.getFeatureSelection();
  logger.fine("Training underlying classifier");
  Classifier c=underlyingClassifierTrainer.train(trainList);
  confusionMatrix=new ConfusionMatrix(new Trial(c,trainList));
  assert (validationSet != null) : "This ClassifierTrainer requires a validation set.";
  Trial t=new Trial(c,validationSet);
  double accuracy=t.getAccuracy();
  InstanceList confidencePredictionTraining=new InstanceList(confidencePredictingPipe);
  logger.fine("Creating confidence prediction instance list");
  double weight;
  for (int i=0; i < t.size(); i++) {
    Classification classification=t.get(i);
    confidencePredictionTraining.add(classification,null,classification.getInstance().getName(),classification.getInstance().getSource());
  }
  logger.info("Begin training ConfidencePredictingClassifier . . . ");
  Classifier cpc=confidencePredictingClassifierTrainer.train(confidencePredictionTraining);
  logger.info("Accuracy at predicting correct/incorrect in training = " + cpc.getAccuracy(confidencePredictionTraining));
  PerLabelInfoGain perLabelInfoGain=new PerLabelInfoGain(trainList);
  this.classifier=new ConfidencePredictingClassifier(c,cpc);
  return classifier;
}
