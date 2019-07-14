public List<SystemDictionaryItem> list(String sn){
  return systemDictionaryService.queryBySn(sn);
}
