/** 
 * ????
 * @param text
 * @param processor
 */
public void parseText(char[] text,IHit<V> processor){
  int position=1;
  int currentState=0;
  for (  char c : text) {
    currentState=getState(currentState,c);
    int[] hitArray=output[currentState];
    if (hitArray != null) {
      for (      int hit : hitArray) {
        processor.hit(position - l[hit],position,v[hit]);
      }
    }
    ++position;
  }
}
