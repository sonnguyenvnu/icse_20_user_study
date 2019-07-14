private HmilyCompensationVO buildByMap(final Map<String,Object> map){
  HmilyCompensationVO vo=new HmilyCompensationVO();
  vo.setTransId((String)map.get("trans_id"));
  vo.setRetriedCount((Integer)map.get("retried_count"));
  vo.setCreateTime(String.valueOf(map.get("create_time")));
  vo.setLastTime(String.valueOf(map.get("last_time")));
  vo.setVersion((Integer)map.get("version"));
  vo.setTargetClass((String)map.get("target_class"));
  vo.setTargetMethod((String)map.get("target_method"));
  vo.setConfirmMethod((String)map.get("confirm_method"));
  vo.setCancelMethod((String)map.get("cancel_method"));
  return vo;
}
