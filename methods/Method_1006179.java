/** 
 * Parses the input from the server to a ParserResult
 * @param input A BufferedReader with a reference to a string with the server's response
 * @throws IOException
 */
private void parse(BufferedReader input) throws IOException {
  BibDatabase bibDatabase=new BibDatabase();
  String recommendations=convertToString(input);
  List<RankedBibEntry> rankedBibEntries=new ArrayList<>();
  JSONObject recommendationsJson=new JSONObject(recommendations).getJSONObject("recommendations");
  Iterator<String> keys=recommendationsJson.keys();
  while (keys.hasNext()) {
    String key=keys.next();
    JSONObject value=recommendationsJson.getJSONObject(key);
    rankedBibEntries.add(populateBibEntry(value));
  }
  rankedBibEntries.sort((  RankedBibEntry rankedBibEntry1,  RankedBibEntry rankedBibEntry2) -> rankedBibEntry1.rank.compareTo(rankedBibEntry2.rank));
  List<BibEntry> bibEntries=rankedBibEntries.stream().map(e -> e.entry).collect(Collectors.toList());
  for (  BibEntry bibentry : bibEntries) {
    bibDatabase.insertEntry(bibentry);
  }
  parserResult=new ParserResult(bibDatabase);
}
