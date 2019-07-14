@Override public Map<String,Object> microQuery(String businessCode){
  Map<String,Object> returnMap=WeiXinMicroUtils.microQuery(businessCode);
  Map<String,Object> paramMap=new HashMap<>();
  paramMap.put("businessCode",businessCode);
  RpMicroSubmitRecord rpMicroSubmitRecord=rpMicroSubmitRecordDao.getBy(paramMap);
  if (StringUtil.isNotNull(returnMap.get("sub_mch_id")) && StringUtil.isEmpty(rpMicroSubmitRecord.getSubMchId())) {
    rpMicroSubmitRecord.setSubMchId(String.valueOf(returnMap.get("sub_mch_id")));
    rpMicroSubmitRecordDao.update(rpMicroSubmitRecord);
  }
  return returnMap;
}
