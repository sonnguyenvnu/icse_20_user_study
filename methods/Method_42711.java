/** 
 * ????????????
 * @param userNo
 * @param auditStatus
 * @return
 */
@Override public RpUserPayConfig getByUserNo(String userNo,String auditStatus){
  return rpUserPayConfigDao.getByUserNo(userNo,auditStatus);
}
