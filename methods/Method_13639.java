@ReadOperation public Map<String,Object> invoke(){
  Map<String,Object> result=new HashMap<>();
  result.put("config",properties);
  Map<String,Object> runtime=new HashMap<>();
  List<AcmPropertySource> all=propertySourceRepository.getAll();
  List<Map<String,Object>> sources=new ArrayList<>();
  for (  AcmPropertySource ps : all) {
    Map<String,Object> source=new HashMap<>();
    source.put("dataId",ps.getDataId());
    source.put("lastSynced",dateFormat.get().format(ps.getTimestamp()));
    sources.add(source);
  }
  runtime.put("sources",sources);
  runtime.put("refreshHistory",refreshHistory.getRecords());
  result.put("runtime",runtime);
  return result;
}
