/** 
 * ????
 * @param instance ??
 */
public void update(Instance instance){
  int[] guessLabel=new int[instance.length()];
  viterbiDecode(instance,guessLabel);
  TagSet tagSet=featureMap.tagSet;
  for (int i=0; i < instance.length(); i++) {
    int[] featureVector=instance.getFeatureAt(i);
    int[] goldFeature=new int[featureVector.length];
    int[] predFeature=new int[featureVector.length];
    for (int j=0; j < featureVector.length - 1; j++) {
      goldFeature[j]=featureVector[j] * tagSet.size() + instance.tagArray[i];
      predFeature[j]=featureVector[j] * tagSet.size() + guessLabel[i];
    }
    goldFeature[featureVector.length - 1]=(i == 0 ? tagSet.bosId() : instance.tagArray[i - 1]) * tagSet.size() + instance.tagArray[i];
    predFeature[featureVector.length - 1]=(i == 0 ? tagSet.bosId() : guessLabel[i - 1]) * tagSet.size() + guessLabel[i];
    update(goldFeature,predFeature);
  }
}
