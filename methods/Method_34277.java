/** 
 * Custom mappings for framework endpoint paths. The keys in the map are the default framework endpoint path, e.g. "/oauth/authorize", and the values are the desired runtime paths.
 * @param patternMap the mappings to set
 */
public void setMappings(Map<String,String> patternMap){
  this.mappings=new HashMap<String,String>(patternMap);
  for (  String key : mappings.keySet()) {
    String result=mappings.get(key);
    if (result.startsWith(FORWARD)) {
      result=result.substring(FORWARD.length());
    }
    if (result.startsWith(REDIRECT)) {
      result=result.substring(REDIRECT.length());
    }
    mappings.put(key,result);
  }
}
