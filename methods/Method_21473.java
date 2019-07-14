@Override public String execute(Client client,Map<String,String> params,QueryAction queryAction) throws Exception {
  Object queryResult=QueryActionElasticExecutor.executeAnyAction(client,queryAction);
  boolean flat=getBooleanOrDefault(params,"flat",false);
  String separator=",";
  if (params.containsKey("separator")) {
    separator=params.get("separator");
  }
  boolean includeScore=getBooleanOrDefault(params,"_score",false);
  boolean includeType=getBooleanOrDefault(params,"_type",false);
  boolean includeId=getBooleanOrDefault(params,"_id",false);
  boolean includeScrollId=getBooleanOrDefault(params,"_scroll_id",false);
  CSVResult result=new CSVResultsExtractor(includeScore,includeType,includeId,includeScrollId).extractResults(queryResult,flat,separator);
  String newLine="\n";
  if (params.containsKey("newLine")) {
    newLine=params.get("newLine");
  }
  String csvString=buildString(separator,result,newLine);
  return csvString;
}
