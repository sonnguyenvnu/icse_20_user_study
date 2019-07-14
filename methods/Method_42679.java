/** 
 * ?????????????????
 * @param userNo
 * @param payWayCode
 * @return
 */
@Override public RpUserPayInfo getByUserNo(String userNo,String payWayCode){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("userNo",userNo);
  paramMap.put("payWayCode",payWayCode);
  return super.getBy(paramMap);
}
