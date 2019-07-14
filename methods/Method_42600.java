@Override public Map<String,Object> microSubmit(RpMicroSubmitRecord rpMicroSubmitRecord){
  rpMicroSubmitRecord.setBusinessCode(StringUtil.get32UUID());
  rpMicroSubmitRecord.setIdCardValidTime("[\"" + rpMicroSubmitRecord.getIdCardValidTimeBegin() + "\",\"" + rpMicroSubmitRecord.getIdCardValidTimeEnd() + "\"]");
  Map<String,Object> returnMap=WeiXinMicroUtils.microSubmit(rpMicroSubmitRecord);
  rpMicroSubmitRecord.setStoreStreet(WxCityNo.getCityNameByNo(rpMicroSubmitRecord.getStoreAddressCode()) + "?" + rpMicroSubmitRecord.getStoreStreet());
  if ("SUCCESS".equals(returnMap.get("result_code"))) {
    saveData(rpMicroSubmitRecord);
  }
  return returnMap;
}
