@SuppressWarnings("unchecked") private Map<String,Object> extractRuleReference(Map<String,Object> sidebar){
  List<Map<String,Object>> entries=(List<Map<String,Object>>)sidebar.get("entries");
  Map<String,Object> entry=entries.get(0);
  List<Map<String,Object>> folders=(List<Map<String,Object>>)entry.get("folders");
  return folders.get(3);
}
