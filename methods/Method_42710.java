/** 
 * ????????????????
 * @param userNo
 * @return
 */
@Override public RpUserPayConfig getByUserNo(String userNo){
  return rpUserPayConfigDao.getByUserNo(userNo,PublicEnum.YES.name());
}
