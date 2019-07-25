/** 
 * ???????
 * @author zxy
 */
public R win(Integer deptId,Long userId,String type,String startTime,String endTime){
  Record record=new Record();
  record.set("deptId",deptId).set("userId",userId).set("type",type).set("startTime",startTime).set("endTime",endTime);
  biTimeUtil.analyzeType(record);
  Integer cycleNum=record.getInt("cycleNum");
  String sqlDateFormat=record.getStr("sqlDateFormat");
  String userIds=record.getStr("userIds");
  List<Record> list=new ArrayList<>();
  if (StrUtil.isEmpty(userIds)) {
    return R.ok().put("data",list);
  }
  Integer beginTime=record.getInt("beginTime");
  StringBuffer sqlStringBuffer=new StringBuffer();
  for (int i=1; i <= cycleNum; i++) {
    sqlStringBuffer.append("select '").append(beginTime).append("' as type,IFNULL((select count(1) from businessview where DATE_FORMAT(create_time,'").append(sqlDateFormat).append("') = '").append(beginTime).append("'and is_end = 1 and owner_user_id in (").append(userIds).append(")),0) as businessEnd,COUNT(1) as businessNum,").append(" IFNULL((select count(1) from businessview where DATE_FORMAT(create_time,'").append(sqlDateFormat).append("') = '").append(beginTime).append("'and is_end = 1 and owner_user_id in (").append(userIds).append(")) / COUNT(1),0 )").append(" as proportion ").append(" from 72crm_crm_business  where DATE_FORMAT(create_time,'").append(sqlDateFormat).append("') = '").append(beginTime).append("' and owner_user_id in (").append(userIds).append(")");
    if (i != cycleNum) {
      sqlStringBuffer.append(" union all ");
    }
    beginTime=biTimeUtil.estimateTime(beginTime);
  }
  List<Record> recordList=Db.find(sqlStringBuffer.toString());
  return R.ok().put("data",recordList);
}
