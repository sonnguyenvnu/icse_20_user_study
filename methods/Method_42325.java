/** 
 * ?????????????????????
 */
@SuppressWarnings("unchecked") private static void startInitFromDB(){
  LOG.info("get data from database");
  int pageNum=1;
  int numPerPage=500;
  PageParam pageParam=new PageParam(pageNum,numPerPage);
  String[] status=new String[]{"101","102","200","201"};
  Integer[] notifyTime=new Integer[]{0,1,2,3,4};
  Map<String,Object> paramMap=new HashMap<String,Object>();
  paramMap.put("statusList",status);
  paramMap.put("notifyTimeList",notifyTime);
  PageBean<RpNotifyRecord> pager=rpNotifyService.queryNotifyRecordListPage(pageParam,paramMap);
  int totalSize=(pager.getNumPerPage() - 1) / numPerPage + 1;
  while (pageNum <= totalSize) {
    List<RpNotifyRecord> list=pager.getRecordList();
    for (int i=0; i < list.size(); i++) {
      RpNotifyRecord notifyRecord=list.get(i);
      notifyRecord.setLastNotifyTime(new Date());
      notifyQueue.addElementToList(notifyRecord);
    }
    pageNum++;
    LOG.info(String.format("??????.rpNotifyService.queryNotifyRecordListPage(%s, %s, %s)",pageNum,numPerPage,paramMap));
    pageParam=new PageParam(pageNum,numPerPage);
    pager=rpNotifyService.queryNotifyRecordListPage(pageParam,paramMap);
  }
}
