/** 
 * Gets the statistic.
 * @return statistic
 */
public JSONObject getStatistic(){
  final JSONObject ret=new JSONObject();
  final Query query=new Query().setFilter(new PropertyFilter(Option.OPTION_CATEGORY,FilterOperator.EQUAL,Option.CATEGORY_C_STATISTIC)).setPageCount(1);
  try {
    final JSONObject result=optionRepository.get(query);
    final JSONArray options=result.optJSONArray(Keys.RESULTS);
    for (int i=0; i < options.length(); i++) {
      final JSONObject option=options.optJSONObject(i);
      ret.put(option.optString(Keys.OBJECT_ID),option.optInt(Option.OPTION_VALUE));
    }
    return ret;
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets statistic failed",e);
    return null;
  }
}
