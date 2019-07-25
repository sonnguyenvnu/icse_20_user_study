/** 
 * Classify an instance using the most frequent class label.
 * @param Instance to be classified. Data field must be a FeatureVector.
 * @return Classification containing the labeling of the instance.
 */
@Override public Classification classify(Instance instance){
  String mostFrequentLabelStr=this.sortedLabelMap.firstEntry().getKey();
  Label mostFrequentLabel=this.labels.get(mostFrequentLabelStr);
  Classification mostFrequentClassification=new Classification(instance,this,mostFrequentLabel);
  return mostFrequentClassification;
}
