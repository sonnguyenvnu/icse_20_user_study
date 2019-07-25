private QueryBuilder preparse(String q,int timezoneOffset){
  q=fixQueryMistakes(q);
  List<String> terms=splitIntoORGroups(q);
  if (terms.size() == 1)   return parse(terms.get(0),timezoneOffset);
  BoolQueryBuilder aquery=QueryBuilders.boolQuery();
  for (  String t : terms) {
    QueryBuilder partial=parse(t,timezoneOffset);
    aquery.must(partial);
  }
  return aquery;
}
