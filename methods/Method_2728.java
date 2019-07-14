/** 
 * ??????
 * @return
 */
public int getTotalFrequency(){
  int frequency=0;
  for (  Integer f : labelMap.values()) {
    frequency+=f;
  }
  return frequency;
}
