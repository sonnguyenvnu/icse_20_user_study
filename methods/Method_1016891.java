/** 
 * Remove features from the sequence that occur fewer than  <code>cutoff</code> times in the corpus, as indicated by  the provided counts. Also swap in the new, reduced alphabet. This method alters the instance in place; it is not appropriate if the original instance will be needed.
 */
public void prune(double[] counts,Alphabet newAlphabet,int cutoff){
  int newLength=0;
  for (int i=0; i < length; i++) {
    if (counts[features[i]] >= cutoff) {
      newLength++;
    }
  }
  int[] newFeatures=new int[newLength];
  int newIndex=0;
  for (int i=0; i < length; i++) {
    if (counts[features[i]] >= cutoff) {
      Object feature=dictionary.lookupObject(features[i]);
      newFeatures[newIndex]=newAlphabet.lookupIndex(feature);
      newIndex++;
    }
  }
  features=newFeatures;
  length=newLength;
  dictionary=newAlphabet;
}
