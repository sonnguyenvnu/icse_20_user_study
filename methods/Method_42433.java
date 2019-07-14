public List<DailyCollectAccountHistoryVo> listDailyCollectAccountHistoryVo(Map<String,Object> params){
  return this.getSessionTemplate().selectList(getStatement("listDailyCollectAccountHistoryVo"),params);
}
