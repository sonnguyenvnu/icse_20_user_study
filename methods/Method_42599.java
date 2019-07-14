@Override public PageBean listPage(PageParam pageParam,RpMicroSubmitRecord rpMicroSubmitRecord){
  Map<String,Object> paramMap=new HashMap<>();
  paramMap.put("storeName",rpMicroSubmitRecord.getStoreName());
  paramMap.put("businessCode",rpMicroSubmitRecord.getBusinessCode());
  paramMap.put("dCardName",rpMicroSubmitRecord.getIdCardName());
  return rpMicroSubmitRecordDao.listPage(pageParam,paramMap);
}
