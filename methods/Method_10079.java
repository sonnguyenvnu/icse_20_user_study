/** 
 * Gets the miscellaneous.
 * @return misc
 */
public List<JSONObject> getMisc(){
  final Query query=new Query().setFilter(new PropertyFilter(Option.OPTION_CATEGORY,FilterOperator.EQUAL,Option.CATEGORY_C_MISC));
  try {
    final JSONObject result=optionRepository.get(query);
    final JSONArray options=result.optJSONArray(Keys.RESULTS);
    for (int i=0; i < options.length(); i++) {
      final JSONObject option=options.optJSONObject(i);
      option.put("label",langPropsService.get(option.optString(Keys.OBJECT_ID) + "Label"));
    }
    return CollectionUtils.jsonArrayToList(options);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets misc failed",e);
    return null;
  }
}
