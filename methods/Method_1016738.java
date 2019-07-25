/** 
 * Classify an instance using random selection based on the trained data.
 * @param Instance to be classified. Data field must be a FeatureVector.
 * @return Classification containing the labeling of the instance.
 */
@Override public Classification classify(Instance instance){
  int max=this.labels.size() - 1;
  Random random=new Random();
  int rndIndex=random.nextInt(max + 1);
  Label randomLabel=this.labels.get(rndIndex);
  Classification randomClassification=new Classification(instance,this,randomLabel);
  return randomClassification;
}
