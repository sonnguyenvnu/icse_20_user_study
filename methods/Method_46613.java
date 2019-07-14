/** 
 * ????TM
 * @return true ????
 */
private boolean prepareToResearchTMCluster(){
  int count=tryConnectCount.incrementAndGet();
  int size=txClientConfig.getManagerAddress().size();
  if (count == size) {
    TMSearcher.search();
    return false;
  }
 else   if (count > size) {
    return !TMSearcher.searchedOne();
  }
  return true;
}
