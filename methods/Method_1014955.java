public R portrait(Integer deptId,Long userId,String type,String startTime,String endTime){
  Record record=new Record();
  record.set("deptId",deptId).set("userId",userId).set("type",type).set("startTime",startTime).set("endTime",endTime);
  biTimeUtil.analyzeType(record);
  String userIds=record.getStr("userIds");
  List<Record> list=new ArrayList<>();
  if (StrUtil.isEmpty(userIds)) {
    return R.ok().put("data",list);
  }
  String[] userIdsArr=userIds.split(",");
  Integer status=biTimeUtil.analyzeType(type);
  list=Db.find(Db.getSqlPara("bi.ranking.portrait",Kv.by("userIds",userIdsArr).set("type",status).set("startTime",startTime).set("endTime",endTime)));
  return R.ok().put("data",list);
}
