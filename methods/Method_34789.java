/** 
 * Return a ref-counted stream that will only do work when at least one subscriber is present
 */
public Observable<HystrixUtilization> observe(){
  return allUtilizationStream;
}
