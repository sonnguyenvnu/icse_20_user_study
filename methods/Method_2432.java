/** 
 * ???????
 * @param interval
 * @return
 */
protected List<Intervalable> checkForOverlapsToTheLeft(Intervalable interval){
  return checkForOverlaps(interval,Direction.LEFT);
}
