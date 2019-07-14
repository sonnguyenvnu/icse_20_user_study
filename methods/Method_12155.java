@Override public PageResult queryDicItem(SystemDictionaryQueryObject qo){
  int count=this.systemDictionaryItemMapper.queryForCount(qo);
  if (count > 0) {
    List<SystemDictionaryItem> list=this.systemDictionaryItemMapper.query(qo);
    return new PageResult(count,qo.getPageSize(),qo.getCurrentPage(),list);
  }
  return PageResult.empty(qo.getPageSize());
}
