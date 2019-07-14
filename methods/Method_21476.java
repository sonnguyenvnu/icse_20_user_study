public CSVResult extractResults(Object queryResult,boolean flat,String separator) throws CsvExtractorException {
  if (queryResult instanceof SearchHits) {
    SearchHit[] hits=((SearchHits)queryResult).getHits();
    List<Map<String,Object>> docsAsMap=new ArrayList<>();
    List<String> headers=createHeadersAndFillDocsMap(flat,hits,null,docsAsMap);
    List<String> csvLines=createCSVLinesFromDocs(flat,separator,docsAsMap,headers);
    return new CSVResult(headers,csvLines);
  }
  if (queryResult instanceof Aggregations) {
    List<String> headers=new ArrayList<>();
    List<List<String>> lines=new ArrayList<>();
    lines.add(new ArrayList<String>());
    handleAggregations((Aggregations)queryResult,headers,lines);
    List<String> csvLines=new ArrayList<>();
    for (    List<String> simpleLine : lines) {
      csvLines.add(Joiner.on(separator).join(simpleLine));
    }
    return new CSVResult(headers,csvLines);
  }
  if (queryResult instanceof SearchResponse) {
    SearchHit[] hits=((SearchResponse)queryResult).getHits().getHits();
    List<Map<String,Object>> docsAsMap=new ArrayList<>();
    List<String> headers=createHeadersAndFillDocsMap(flat,hits,((SearchResponse)queryResult).getScrollId(),docsAsMap);
    List<String> csvLines=createCSVLinesFromDocs(flat,separator,docsAsMap,headers);
    return new CSVResult(headers,csvLines);
  }
  return null;
}
