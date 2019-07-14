/** 
 * ?????
 * @return is searched one
 */
public static boolean searchedOne(){
  if (Objects.nonNull(clusterCountLatch)) {
    if (clusterCountLatch.getCount() == 0) {
      return false;
    }
    clusterCountLatch.countDown();
    return true;
  }
  return false;
}
