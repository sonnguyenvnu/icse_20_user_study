@Override public RpUserPayConfig getByUserNo(String userNo,String auditStatus){
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("userNo",userNo);
  paramMap.put("status",PublicStatusEnum.ACTIVE.name());
  paramMap.put("auditStatus",auditStatus);
  return super.getBy(paramMap);
}
