static String getFusionTableKey(URL url){
  String tableId=getParamValue(url,"dsrcid");
  if (tableId == null || tableId.isEmpty()) {
    tableId=getParamValue(url,"docid");
  }
  return tableId;
}
