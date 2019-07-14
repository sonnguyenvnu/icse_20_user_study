/** 
 * build  TccCompensationVO.
 * @param adapter {@linkplain CoordinatorRepositoryAdapter}
 * @return {@linkplain HmilyCompensationVO}
 */
public static HmilyCompensationVO buildVO(final CoordinatorRepositoryAdapter adapter){
  HmilyCompensationVO vo=new HmilyCompensationVO();
  vo.setTransId(adapter.getTransId());
  vo.setCreateTime(DateUtils.parseDate(adapter.getCreateTime()));
  vo.setRetriedCount(adapter.getRetriedCount());
  vo.setLastTime(DateUtils.parseDate(adapter.getLastTime()));
  vo.setVersion(adapter.getVersion());
  vo.setTargetClass(adapter.getTargetClass());
  vo.setTargetMethod(adapter.getTargetMethod());
  vo.setConfirmMethod(adapter.getConfirmMethod());
  vo.setCancelMethod(adapter.getCancelMethod());
  return vo;
}
