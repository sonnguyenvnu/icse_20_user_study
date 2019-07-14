/** 
 * ?????
 * @param enable true????[??CPU???]????false?????
 * @return
 */
public Segment enableMultithreading(boolean enable){
  if (enable)   config.threadNumber=Runtime.getRuntime().availableProcessors();
 else   config.threadNumber=1;
  return this;
}
