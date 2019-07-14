@ReadOperation public Map<String,Object> invoke(){
  Map<String,Object> result=new HashMap<>(16);
  result.put("NacosConfigProperties",properties);
  List<NacosPropertySource> all=NacosPropertySourceRepository.getAll();
  List<Map<String,Object>> sources=new ArrayList<>();
  for (  NacosPropertySource ps : all) {
    Map<String,Object> source=new HashMap<>(16);
    source.put("dataId",ps.getDataId());
    source.put("lastSynced",dateFormat.get().format(ps.getTimestamp()));
    sources.add(source);
  }
  result.put("Sources",sources);
  result.put("RefreshHistory",refreshHistory.getRecords());
  return result;
}
