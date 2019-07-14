/** 
 * @param ps
 */
static protected void preparePreferenceStore(PreferenceStore ps){
  ps.put("scripting.expressions",new TopList(s_expressionHistoryMax));
  ps.put("scripting.starred-expressions",new TopList(Integer.MAX_VALUE));
}
