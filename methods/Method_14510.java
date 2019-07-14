protected void storeCell(List<Object[]> rows,int row,int col,ObjectNode obj,Map<String,ReconCandidate> reconCandidateMap){
  String id=obj.get("id").asText();
  ReconCandidate rc;
  if (reconCandidateMap.containsKey(id)) {
    rc=reconCandidateMap.get(id);
  }
 else {
    rc=new ReconCandidate(obj.get("id").asText(),obj.get("name").asText(),JSONUtilities.getStringArray(obj,"type"),100);
    reconCandidateMap.put(id,rc);
  }
  storeCell(rows,row,col,rc);
}
