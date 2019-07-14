public Map<String,ReconciledDataExtensionJob.DataExtension> extend(Set<String> ids,Map<String,ReconCandidate> reconCandidateMap) throws Exception {
  StringWriter writer=new StringWriter();
  formulateQuery(ids,extension,writer);
  String query=writer.toString();
  InputStream is=performQuery(this.endpoint,query);
  try {
    ObjectNode o=ParsingUtilities.mapper.readValue(is,ObjectNode.class);
    if (columns.size() == 0) {
      List<ColumnInfo> newColumns=ParsingUtilities.mapper.convertValue(o.get("meta"),new TypeReference<List<ColumnInfo>>(){
      }
);
      columns.addAll(newColumns);
    }
    Map<String,ReconciledDataExtensionJob.DataExtension> map=new HashMap<String,ReconciledDataExtensionJob.DataExtension>();
    if (o.has("rows") && o.get("rows") instanceof ObjectNode) {
      ObjectNode records=(ObjectNode)o.get("rows");
      for (      String id : ids) {
        if (records.has(id) && records.get(id) instanceof ObjectNode) {
          ObjectNode record=(ObjectNode)records.get(id);
          ReconciledDataExtensionJob.DataExtension ext=collectResult(record,reconCandidateMap);
          if (ext != null) {
            map.put(id,ext);
          }
        }
      }
    }
    return map;
  }
  finally {
    is.close();
  }
}
