@Override public TxLogList txLogList(Integer page,Integer limit,String groupId,String tag,String startTime,String stopTime,Integer timeOrder) throws TxManagerException {
  if (Objects.isNull(page) || page < 1) {
    page=1;
  }
  if (Objects.isNull(limit) || limit < 1) {
    limit=10;
  }
  if (Objects.isNull(timeOrder) || timeOrder < 1 || timeOrder > 2) {
    timeOrder=2;
  }
  List<Field> list=Stream.of(new GroupId(groupId),new Tag(tag),new StartTime(startTime),new StopTime(stopTime)).filter(Field::ok).collect(Collectors.toList());
  LogList logList;
  try {
    logList=txLoggerHelper.findByLimitAndFields(page,limit,timeOrder,list);
  }
 catch (  TxLoggerException e) {
    throw new TxManagerException(e);
  }
  List<TxManagerLog> txManagerLogs=new ArrayList<>(logList.getTxLogs().size());
  for (  TxLog txLog : logList.getTxLogs()) {
    TxManagerLog txManagerLog=new TxManagerLog();
    BeanUtils.copyProperties(txLog,txManagerLog);
    txManagerLogs.add(txManagerLog);
  }
  TxLogList txLogList=new TxLogList();
  txLogList.setTotal(logList.getTotal());
  txLogList.setLogs(txManagerLogs);
  return txLogList;
}
