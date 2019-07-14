/** 
 * ??????????????
 * @param userNO ????
 * @param isPessimist ?????
 * @return
 */
private RpAccount getByUserNo_IsPessimist(String userNo,boolean isPessimist){
  Map<String,Object> map=new HashMap<String,Object>();
  map.put("userNo",userNo);
  map.put("isPessimist",isPessimist);
  return rpAccountDao.getByUserNo(map);
}
