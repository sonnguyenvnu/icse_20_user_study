@Override public int insert(TxLog txLoggerInfo){
  if (logDbProperties.isEnabled()) {
    String sql="insert into t_logger(group_id,unit_id,tag,content,create_time,app_name) values(?,?,?,?,?,?)";
    return dbHelper.update(sql,txLoggerInfo.getGroupId(),txLoggerInfo.getUnitId(),txLoggerInfo.getTag(),Strings.format(txLoggerInfo.getContent(),Maps.of("xid",txLoggerInfo.getGroupId(),"uid",txLoggerInfo.getUnitId()),txLoggerInfo.getArgs()),txLoggerInfo.getCreateTime(),txLoggerInfo.getAppName());
  }
 else {
    throw new NotEnableLogException("not enable logger");
  }
}
