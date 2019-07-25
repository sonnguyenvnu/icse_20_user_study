/** 
 * Returns the profile results for this search response (including all shards). An empty map is returned if profiling was not enabled
 * @return Profile results
 */
public final Map<String,ProfileShardResult> profile(){
  if (profileResults == null) {
    return Collections.emptyMap();
  }
  return profileResults.getShardResults();
}
