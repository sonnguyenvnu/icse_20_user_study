/** 
 * ??????
 * @param ScratchPoolList
 */
public void savaListDate(List<RpAccountCheckMistakeScratchPool> scratchPoolList){
  for (  RpAccountCheckMistakeScratchPool record : scratchPoolList) {
    rpAccountCheckMistakeScratchPoolDao.insert(record);
  }
}
