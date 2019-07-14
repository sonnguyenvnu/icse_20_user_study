@Override public PageResult queryDic(SystemDictionaryQueryObject qo){
  int count=this.systemDictionaryMapper.queryForCount(qo);
  if (count > 0) {
    List<SystemDictionary> list=this.systemDictionaryMapper.query(qo);
    return new PageResult(count,qo.getPageSize(),qo.getCurrentPage(),list);
  }
  return PageResult.empty(qo.getPageSize());
}
