/** 
 * ??????
 * @return
 */
public Segment enableIndexMode(boolean enable){
  config.indexMode=enable ? 2 : 0;
  return this;
}
