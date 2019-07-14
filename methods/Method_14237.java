/** 
 * Run relevance searches for the first n cells in the given column and count the types of the results. Return a sorted list of types, from most frequent to least. 
 * @param project
 * @param column
 * @return
 * @throws JSONException , IOException 
 */
protected List<TypeGroup> guessTypes(Project project,Column column,String serviceUrl) throws IOException {
  Map<String,TypeGroup> map=new HashMap<String,TypeGroup>();
  int cellIndex=column.getCellIndex();
  List<String> samples=new ArrayList<String>(SAMPLE_SIZE);
  Set<String> sampleSet=new HashSet<String>();
  for (  Row row : project.rows) {
    Object value=row.getCellValue(cellIndex);
    if (ExpressionUtils.isNonBlankData(value)) {
      String s=value.toString().trim();
      if (!sampleSet.contains(s)) {
        samples.add(s);
        sampleSet.add(s);
        if (samples.size() >= SAMPLE_SIZE) {
          break;
        }
      }
    }
  }
  Map<String,IndividualQuery> queryMap=new HashMap<>();
  for (int i=0; i < samples.size(); i++) {
    queryMap.put("q" + i,new IndividualQuery(samples.get(i),3));
  }
  String queriesString=ParsingUtilities.defaultWriter.writeValueAsString(queryMap);
  try {
    URL url=new URL(serviceUrl);
    HttpURLConnection connection=(HttpURLConnection)url.openConnection();
{
      connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
      connection.setConnectTimeout(30000);
      connection.setDoOutput(true);
      DataOutputStream dos=new DataOutputStream(connection.getOutputStream());
      try {
        String body="queries=" + ParsingUtilities.encode(queriesString);
        dos.writeBytes(body);
      }
  finally {
        dos.flush();
        dos.close();
      }
      connection.connect();
    }
    if (connection.getResponseCode() >= 400) {
      InputStream is=connection.getErrorStream();
      throw new IOException("Failed  - code:" + Integer.toString(connection.getResponseCode()) + " message: " + is == null ? "" : ParsingUtilities.inputStreamToString(is));
    }
 else {
      InputStream is=connection.getInputStream();
      try {
        String s=ParsingUtilities.inputStreamToString(is);
        ObjectNode o=ParsingUtilities.evaluateJsonStringToObjectNode(s);
        Iterator<JsonNode> iterator=o.iterator();
        while (iterator.hasNext()) {
          JsonNode o2=iterator.next();
          if (!(o2.has("result") && o2.get("result") instanceof ArrayNode)) {
            continue;
          }
          ArrayNode results=(ArrayNode)o2.get("result");
          List<ReconResult> reconResults=ParsingUtilities.mapper.convertValue(results,new TypeReference<List<ReconResult>>(){
          }
);
          int count=reconResults.size();
          for (int j=0; j < count; j++) {
            ReconResult result=reconResults.get(j);
            double score=1.0 / (1 + j);
            List<ReconType> types=result.types;
            int typeCount=types.size();
            for (int t=0; t < typeCount; t++) {
              ReconType type=types.get(t);
              double score2=score * (typeCount - t) / typeCount;
              if (map.containsKey(type.id)) {
                TypeGroup tg=map.get(type.id);
                tg.score+=score2;
                tg.count++;
              }
 else {
                map.put(type.id,new TypeGroup(type.id,type.name,score2));
              }
            }
          }
        }
      }
  finally {
        is.close();
      }
    }
  }
 catch (  IOException e) {
    logger.error("Failed to guess cell types for load\n" + queriesString,e);
    throw e;
  }
  List<TypeGroup> types=new ArrayList<TypeGroup>(map.values());
  Collections.sort(types,new Comparator<TypeGroup>(){
    @Override public int compare(    TypeGroup o1,    TypeGroup o2){
      int c=Math.min(SAMPLE_SIZE,o2.count) - Math.min(SAMPLE_SIZE,o1.count);
      if (c != 0) {
        return c;
      }
      return (int)Math.signum(o2.score / o2.count - o1.score / o1.count);
    }
  }
);
  return types;
}
