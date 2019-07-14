/** 
 * ????
 * @param position
 * @param currentState
 * @param collectedEmits
 */
private void storeEmits(int position,int currentState,List<Hit<V>> collectedEmits){
  int[] hitArray=output[currentState];
  if (hitArray != null) {
    for (    int hit : hitArray) {
      collectedEmits.add(new Hit<V>(position - l[hit],position,v[hit]));
    }
  }
}
