/** 
 * Remove features from the sequence that occur fewer than  <code>cutoff</code> times in the corpus, as indicated by  the provided counts. Also swap in the new, reduced alphabet. This method alters the instance in place; it is not appropriate if the original instance will be needed.
 */
public void prune(Alphabet newAlphabet){
  int newLength=0;
  boolean[] keepers=new boolean[length];
  for (int i=0; i < length; i++) {
    if (newAlphabet.contains(dictionary.lookupObject(features[i]))) {
      keepers[i]=true;
      newLength++;
    }
  }
  int[] newFeatures=new int[newLength];
  int newIndex=0;
  for (int i=0; i < length; i++) {
    if (keepers[i]) {
      newFeatures[newIndex]=newAlphabet.lookupIndex(dictionary.lookupObject(features[i]));
      newIndex++;
    }
  }
  features=newFeatures;
  length=newLength;
  dictionary=newAlphabet;
}
