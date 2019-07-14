/** 
 * Fills the side ad labels.
 * @param dataModel the specified data model
 */
private void fillSideAd(final Map<String,Object> dataModel){
  final JSONObject adOption=optionQueryService.getOption(Option.ID_C_SIDE_FULL_AD);
  if (null == adOption) {
    dataModel.put("ADLabel","");
  }
 else {
    dataModel.put("ADLabel",adOption.optString(Option.OPTION_VALUE));
  }
}
